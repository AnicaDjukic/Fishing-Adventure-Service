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
          <h3>New review</h3>
          <button type="button" class="btn-close" data-bs-dismiss="modal">
            <i class="fas fa-times fa-lg"></i>
          </button>
        </div>
        <div
          class="col-md-5"
          style="margin-left: 28%; margin-top: 3%; margin-bottom: 2%"
        >
          <div style="display: flex">
            <span class="input-group-text">Rate</span>
            <div
              class="rating-div form-control"
              style="min-width: 135px; padding: 0"
              v-on:click="searchCottage"
            >
              <div class="rating" style="height: 0">
                <input
                  type="radio"
                  v-on:click="rateService('star1')"
                  name="star"
                  id="star1"
                  value="5"
                />
                <label for="star1"></label>
                <input
                  type="radio"
                  v-on:click="rateService('star2')"
                  name="star"
                  id="star2"
                  value="4"
                />
                <label for="star2"></label>
                <input
                  type="radio"
                  name="star"
                  v-on:click="rateService('star3')"
                  id="star3"
                  value="3"
                />
                <label for="star3"></label>
                <input
                  type="radio"
                  name="star"
                  v-on:click="rateService('star4')"
                  id="star4"
                  value="2"
                />
                <label for="star4"></label>
                <input
                  type="radio"
                  name="star"
                  v-on:click="rateService('star5')"
                  id="star5"
                  value="1"
                />
                <label for="star5"></label>
              </div>
            </div>
          </div>
        </div>
        <div class="modal-body">
          <h6>Leave your comment about {{ reservation.serviceName }}:</h6>
          <textarea
            class="login-inputs-textarea"
            placeholder="Enter comment here.."
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
            v-on:click="createReview"
          >
            Send review
          </button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios";
export default {
  components: {},
  props: ["reservation"],
  name: "ReviewModal",
  data: function () {
    return {
      content: "",
      rate: 0,
      error: "",
    };
  },
  mounted: function () {},
  methods: {
    rateService: function (id) {
      var ele = document.getElementById(id);
      this.rate = ele.value;
    },
    createReview: function () {
      this.error = "";
      if (this.content == "" || this.rate == 0) {
        this.error = "You need to enter rate and comment!";
        return;
      }

      let review = {
        reservationId: this.reservation.id,
        content: this.content,
        rate: this.rate,
        serviceId: this.reservation.serviceId,
      };
      axios
        .post("http://localhost:8080/review/new", review, {
          headers: {
            "Access-Control-Allow-Origin": "http://localhost:8080",
            Authorization: "Bearer " + localStorage.refreshToken,
          },
        })
        .then(() => {
          this.$toast.show("Thank you for your review!", {
            duration: 2000,
          });

          window.location.reload();
        });
    },
  },
};
</script>

<style scoped src="@/css/newCottageModal.css"></style>
