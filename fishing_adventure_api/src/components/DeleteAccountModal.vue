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
export default {
  components: {},
  name: "DeleteAccountModal",
  data: function () {
    return {};
  },
  mounted: function () {},
  methods: {
    createRequest: function () {
      this.$toast.show(
        "Request sent and waiting for review. You will be notified by email about the status of your account.",
        {
          duration: 7000,
        }
      );
    },
  },
};
</script>

<style scoped src="@/css/newCottageModal.css"></style>
