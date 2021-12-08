<template>
  <div>
    <div class="card mb-3 bg-dark mt-3" style="width: 65%; margin: auto">
      <div class="row g-0">
        <div class="col-md-4 shadow-none">
          <img
            style="width: 100%; height: 225px; object-fit: cover"
            src="@/assets/c2.jpg"
            class="img-fluid rounded-start shadow-none"
          />
        </div>

        <div class="col-md-8 shadow-none" name="main-col">
          <div class="card-body shadow-none">
            <div class="card-text shadow-none" style="display: flex">
              <h5 class="card-title shadow-none">Villa Madam</h5>
              <p class="reservationStatus shadow-none" v-if="review != true">
                <b class="shadow-none">Panding</b>
              </p>
              <p
                class="reservationStatus shadow-none"
                style="background-color: #0c442a"
                v-if="review == true"
              >
                <b class="shadow-none">Finished</b>
              </p>
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
                    @alexaLopez
                  </p>
                </div>
                <div class="row shadow-none">
                  <p class="card-text text-left shadow-none col-md-4">
                    Period:
                  </p>
                  <p class="card-text text-left shadow-none col-md-8">
                    {{ date }}
                  </p>
                </div>
                <div class="row shadow-none">
                  <p class="card-text text-left shadow-none col-md-4">Price:</p>
                  <p class="card-text text-left shadow-none col-md-8">
                    50 <i class="fas fa-dollar-sign"></i>
                  </p>
                </div>
                <div class="row shadow-none">
                  <p class="card-text text-left shadow-none col-md-4">
                    Persons:
                  </p>
                  <p class="card-text text-left shadow-none col-md-8">2</p>
                </div>
                <p class="card-text text-left shadow-none">
                  Additional services:
                </p>

                <div class="row shadow-none" style="margin-left: 1%">
                  <p class="additionalServices shadow-none">
                    <i class="fas fa-wifi fa-xs shadow-none"></i> WiFi
                  </p>
                  <p class="additionalServices shadow-none">
                    <i class="fas fa-tv fa-xs shadow-none"></i> TV
                  </p>
                  <p class="additionalServices shadow-none">
                    <i class="fas fa-fan fa-xs shadow-none"></i> Air Conditioner
                  </p>
                  <p class="additionalServices shadow-none">
                    <i class="fas fa-parking fa-xs shadow-none"></i> Private
                    parking
                  </p>
                  <p class="additionalServices shadow-none">
                    <i class="fas fa-glass-martini-alt"></i> Bar
                  </p>
                </div>
              </div>

              <div class="manageReservation shadow-none">
                <button
                  v-if="review != true"
                  class="btn btn-primary shadow-none mb-2"
                  style="background-color: #0c442a; border-color: #0c442a"
                >
                  Create
                </button>
                <button
                  v-if="review != true"
                  class="btn btn-primary shadow-none"
                  style="
                    background-color: rgb(94 23 30);
                    border-color: rgb(94 23 30);
                  "
                >
                  Reject
                </button>
                <button
                  v-if="review == true"
                  class="btn btn-primary shadow-none"
                  style="
                    background-color: rgb(255 217 0 / 59%);
                    border-color: rgb(255 217 0 / 59%);
                  "
                >
                  Review
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
export default {
  props: ["review"],
  data: function () {
    return {
      date: "12/20/2021 - 12/25/2021",
    };
  },
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
