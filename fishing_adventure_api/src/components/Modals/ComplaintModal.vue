<template>
  <div
    class="modal fade dark"
    id="DeleteAccountModal"
    aria-labelledby="DeleteAccountModalLabel"
    aria-hidden="true"
    data-bs-backdrop="static"
    data-bs-keyboard="false"
    v-on:show="closeModal"
  >
    <div class="modal-dialog modal-dialog-centered">
      <div class="modal-content" style="height: 30rem">
        <div class="modal-header">
          <h3>New complaint</h3>
          <button type="button" class="btn-close" data-bs-dismiss="modal">
            <i class="fas fa-times fa-lg"></i>
          </button>
        </div>
        <div
          class="col-md-5"
          style="margin-left: 28%; margin-top: 3%; margin-bottom: 2%"
        ></div>
        <div class="modal-body">
          <h6>Leave your complaint about {{ reservation.serviceName }}:</h6>
          <textarea
            class="login-inputs-textarea"
            placeholder="Enter complaint here.."
            v-model="content"
          />

          <h6 style="color: red">
            <b>{{ error }}</b>
          </h6>
        </div>

        <div class="modal-footer">
          <button
            type="button"
            class="btn btn-outline-primary"
            v-on:click="createComplaint"
          >
            Send complaint
          </button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios";
axios.defaults.baseURL = process.env.VUE_APP_URL;
export default {
  components: {},
  props: ["reservation"],
  name: "ComplaintModal",
  data: function () {
    return {
      content: "",
      error: "",
    };
  },
  mounted: function () {},
  methods: {
    createComplaint: function () {
      this.error = "";
      if (this.content == "") {
        this.error = "You need to enter complaint!";
        return;
      }

      let complaint = {
        reservationId: this.reservation.id,
        content: this.content,
        serviceId: this.reservation.serviceId,
      };
      axios
        .post("/complaint/new", complaint, {
          headers: {
            "Access-Control-Allow-Origin": process.env.VUE_APP_URL,
            Authorization: "Bearer " + localStorage.refreshToken,
          },
        })
        .then(() => {
          this.$toast.show("Thank you for your complaint!", {
            duration: 2000,
          });

          window.location.reload();
        });
    },
  },
};
</script>

<style scoped src="@/css/newCottageModal.css"></style>
