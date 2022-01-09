<template>
  <div>
    <div class="card mb-3 bg-dark mt-3" style="width: 65%; margin: auto">
      <div class="row g-0">
        <div class="col-md-4 shadow-none">
          <img
            style="width: 100%; height: 225px; object-fit: cover"
            :src="require('@/assets/' + reservation.imagePath)"
            class="img-fluid rounded-start shadow-none"
          />
        </div>
        <div class="col-md-8 shadow-none" name="main-col">
          <div class="card-body shadow-none">
            <div class="card-text shadow-none" style="display: flex">
              <h5 class="card-title shadow-none">{{ reservation.serviceName }}</h5>
            </div>
            <div
              class="card-text shadow-none"
              style="display: flex; align-items: center"
            >
              <div class="shadow-none" style="width: 100%">
                <div class="row shadow-none">
                  <p class="card-text text-left shadow-none col-md-4">
                    Client:
                  </p>
                  <p class="advertiserTitle shadow-none col-md-6">
                    {{ client.email }}
                  </p>
                </div>
                <div class="row shadow-none">
                  <p class="card-text text-left shadow-none col-md-4">
                    Start date:
                  </p>
                  <p class="card-text text-left shadow-none col-md-8">
                    {{ startDate }}
                  </p>
                </div>
                <div class="row shadow-none">
                  <p class="card-text text-left shadow-none col-md-4">
                    End date:
                  </p>
                  <p class="card-text text-left shadow-none col-md-8">
                    {{ endDate }}
                  </p>
                </div>
                <div class="row shadow-none">
                  <p class="card-text text-left shadow-none col-md-4">Price:</p>
                  <p class="card-text text-left shadow-none col-md-8">
                    {{ price }} <i class="fas fa-dollar-sign"></i>
                  </p>
                </div>
                <div class="row shadow-none">
                  <p class="card-text text-left shadow-none col-md-4">
                    Persons:
                  </p>
                  <p class="card-text text-left shadow-none col-md-8">
                    {{ persons }}
                  </p>
                </div>
                <p class="card-text text-left shadow-none">
                  Additional services:
                </p>

                <div class="row shadow-none" style="margin-left: 1%">
                  <p
                    v-for="additionalService in reservation.chosenServices"
                    :key="additionalService.id"
                    class="additionalServices shadow-none"
                  >
                    <i class="shadow-none"></i> {{ additionalService.name }}
                  </p>
                </div>
              </div>

              <div class="manageReservation shadow-none">
                <button
                  v-if="current == false"
                  :disabled="reservation.reviewId != null"
                  class="btn btn-primary shadow-none mb-2"
                  style="
                    background-color: rgb(0 102 102);
                    border-color: rgb(0 102 102);
                  "
                  v-on:click="emitReview"
                  data-bs-toggle="modal"
                  :data-bs-target="'#reviewAccount'"
                >
                  Review
                </button>
              </div>

              <div class="manageReservation shadow-none">
                <button
                  v-if="current == false"
                  class="btn btn-primary shadow-none mb-2"
                  style="
                    background-color: rgb(94 23 30);
                    border-color: rgb(94 23 30);
                  "
                >
                  Complaint
                </button>
                <button
                  class="btn btn-primary shadow-none"
                  v-if="current == true"
                  :disabled="review != true"
                  style="
                    background-color: rgb(94 23 30);
                    border-color: rgb(94 23 30);
                  "
                >
                  Cancel
                </button>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios";
import moment from "moment";
export default {
  props: ["reservation", "review", "current"],
  data: function () {
    return {
      client: {},
      startDate: {},
      endDate: {},
      price: 0.0,
      persons: 0,
    };
  },
  mounted: function () {
    axios
      .get("http://localhost:8080/users/get", {
        headers: {
          "Access-Control-Allow-Origin": "http://localhost:8080",
          Authorization: "Bearer " + localStorage.refreshToken,
        },
      })
      .then((res) => {
        this.client = res.data;
      });
    this.startDate = moment(this.reservation.startDate).format(
      "DD MMM YYYY hh:mm a"
    );
    this.endDate = moment(this.reservation.endDate).format(
      "DD MMM YYYY hh:mm a"
    );
    this.price = this.reservation.price;
    this.persons = this.reservation.persons;
  },
  methods: {
    emitReview: function() {
      this.$emit('emitReview', this.reservation);
    }
  }
};
</script>
<style scoped>
.img-fluid {
  height: 100%;
}
h5 {
  color: white;
}
p {
  margin-bottom: 2%;
  color: gray;
}
@media (min-width: 768px) {
  .col-md-4 {
    flex: 0 0 auto;
    width: 25%;
  }
}
@media (min-width: 768px) {
  .col-md-8 {
    flex: 0 0 auto;
    width: 74.666667%;
  }
}
div {
  text-align: left;
}

.row {
  align-items: center;
}
.card :hover {
  cursor: pointer;
  box-shadow: 2px 2px 15px 2px rgba(187, 178, 178, 0.664);
  transition: 0.5s;
}

.advertiserTitle {
  background-color: rgba(128, 128, 128, 0.226);
  color: gray;
  padding: 1px 5px;
  border-radius: 5px;
  margin-left: 3%;
  width: fit-content;
}

.additionalServices {
  background-color: rgb(0 0 0 / 35%);
  color: gray;
  padding: 1px 5px;
  border-radius: 5px;
  width: fit-content;
  margin: 2px;
}

.manageReservation {
  display: flex;
  flex-direction: column;
  margin-right: 1rem;
}

.card-body {
  padding: 1rem 0 1rem 1rem;
}

.discountStatus {
  margin: auto 0px auto auto;
  text-align: right;
  padding: 0 10px;
  background-color: #b1090966;
  width: 30%;
  text-align: center;
  color: white;
  border-radius: 5px 0px 0px 5px;
  text-decoration: bold;
}

.reservationStatus {
  margin: auto 0px auto auto;
  text-align: right;
  padding: 0 10px;
  background-color: #09b1a966;
  width: 30%;
  text-align: center;
  color: white;
  border-radius: 5px 0px 0px 5px;
  text-decoration: bold;
}

.btn-primary:hover,
.advertiserTitle:hover {
  box-shadow: 3px 5px 0px 0px rgb(22 20 20 / 66%) !important;
  transition: 0.5s;
}

.card-text {
  margin: 0;
}
</style>
