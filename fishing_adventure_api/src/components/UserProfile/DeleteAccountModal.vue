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
          <h3>Delete account</h3>
          <button type="button" class="btn-close" data-bs-dismiss="modal">
            <i class="fas fa-times fa-lg"></i>
          </button>
        </div>
        <div class="modal-body">
          <h6>Tell us why you want to delete your account:</h6>
          <textarea
            class="login-inputs-textarea"
            placeholder="Enter text here.."
            v-model="content"
          />
          <br />
        </div>

        <div class="modal-footer">
          <button
            type="button"
            class="btn btn-outline-primary"
            v-on:click="createRequest"
            data-bs-dismiss="modal"
          >
            Send request
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
  emits: ["deleterequestsent"],
  name: "DeleteAccountModal",
  data: function () {
    return {
      content: "",
    };
  },
  mounted: function () {},
  methods: {
    createRequest: function () {
      let request = {
        content: this.content,
      };
      axios
        .post("/deleteRequest/create", request, {
          headers: {
            "Access-Control-Allow-Origin": process.env.VUE_APP_URL,
            Authorization: "Bearer " + localStorage.refreshToken,
          },
        })
        .then(() => {
          this.$emit("deleterequestsent", true);
          this.$toast.show(
            "Request sent and waiting for review. You will be notified by email about the status of your account.",
            {
              duration: 7000,
            }
          );
        });
    },
  },
};
</script>

<style scoped src="@/css/newCottageModal.css"></style>
