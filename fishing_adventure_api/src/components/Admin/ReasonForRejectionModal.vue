<template>
  <div
    class="modal fade dark"
    id="ReasonForRejectionModal"
    aria-labelledby="OfferModalLabel"
    aria-hidden="true"
    data-bs-backdrop="static"
    data-bs-keyboard="false"
    v-on:show="closeModal"
  >
    <div class="modal-dialog modal-dialog-centered">
      <div class="modal-content" style="height: 20.5rem">
        <div class="modal-header">
          <h3 style="font-size: 23px">
            Reason for rejection
          </h3>
          <button
            type="button"
            class="btn-close"
            data-bs-dismiss="modal"
            v-on:click="closeModal"
          >
            <i class="fas fa-times fa-lg"></i>
          </button>
        </div>
        <div class="modal-body">
            <textarea
            class="login-inputs-textarea"
            placeholder="Enter reason here.."
            v-model="reason"
            style="max-height: 10rem; margin-top:20px"
            />

          <h6 style="color: red; margin-top: 1rem">
            <b>{{ error }}</b>
          </h6>
        </div>
        <div class="modal-footer steps-div">
          <button
            type="button"
            class="btn btn-outline-primary"
            v-on:click="confirmRejection"
          >
            Confirm rejection
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
  props: ["email"],
  name: "ReasonForRejectionModal",
  data: function () {
    return {
      reason: "",
      error: ""
    };
  },
  methods: {
    closeModal: function () {
      this.reason = "";
      this.error = "";
    },
    confirmRejection: function () {
        if (this.reason == "") {
            this.error = "You must enter a reason for rejection.";
        }
        else {
             axios
                .get(
                "/users/rejectRegistrationRequest?email=" +
                    this.email + "&reason=" + this.reason,
                {
                    headers: {
                    "Access-Control-Allow-Origin": process.env.VUE_APP_URL,
                    Authorization: "Bearer " + localStorage.refreshToken,
                    },
                }
                )
                .then(window.location.reload());
        }
    },
  },
};
</script>

<style scoped src="@/css/newCottageModal.css"></style>
