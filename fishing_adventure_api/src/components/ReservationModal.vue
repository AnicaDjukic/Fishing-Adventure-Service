<template>
  <div
    class="modal fade dark"
    id="NewCottageModal"
    aria-labelledby="NewCottageModalLabel"
    aria-hidden="true"
    data-bs-backdrop="static"
    data-bs-keyboard="false"
    v-on:show="closeModal"
  >
    <div class="modal-dialog modal-dialog-centered">
      <div class="modal-content">
        <div class="modal-header">
          <h3>New reservation</h3>
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
          <h6 style="color: white; margin-top: 5%">
            <b>Select date range:</b>
          </h6>
          <Datepicker
            style="
              width: 100%;
              margin-right: 10px;
              border: 1px solid white;
              border-radius: 5px;
            "
            dark
            id="picker"
            v-if="selectedDateRange == undefined"
            v-model="dateRange"
            v-on:click="selectDate"
            range
            :enableTimePicker="true"
          ></Datepicker>

          <Datepicker
            style="
              width: 100%;
              margin-right: 10px;
              border: 1px solid white;
              border-radius: 5px;
            "
            dark
            id="picker"
            v-if="selectedDateRange != undefined"
            v-model="selectedDateRange"
            range
            calendar-button=false
            :enableTimePicker="true"
          ></Datepicker>

          <h6 style="color: white; margin-top: 10%"><b>Persons:</b></h6>
          <input
            type="number"
            min="1"
            v-if="numOfPersons == undefined"
            v-model="numberOfPersons"
            v-on:click="changePersons"
            style="text-align: center"
            class="form-control"
          />
          <input
            type="number"
            min="1"
            v-if="numOfPersons != undefined"
            v-model="numOfPersons"
            v-on:change="checkPersons"
            style="text-align: center"
            class="form-control"
          />

          <h6 style="color: white; margin-top: 10%">
            <b>Select additional services:</b>
          </h6>
          <div id="table-wrapper" style="margin-top: -5%">
            <div id="table-scroll">
              <table class="table">
                <tbody>
                  <tr v-if="addServices == undefined || !addServices.length" style="color: white;"> No additional services available </tr>
                  <tr
                    v-for="additionalService in additionalServices"
                    :key="additionalService.id"
                  >
                    <td>
                      <label style="color: white">{{
                        additionalService.name
                      }}</label>
                    </td>
                    <td>
                      <label style="color: white"
                        >{{ additionalService.price }} $</label
                      >
                    </td>
                    <td>
                      <input
                        :id="additionalService.id"
                        type="checkbox"
                        v-on:click="check(additionalService)"
                      />
                    </td>
                  </tr>
                </tbody>
              </table>
            </div>
          </div>
          <h5 style="color: white; margin-top: 5%" v-if="totalPrice != 0">
            <b>~ Total price: {{ totalPrice }}$ ~</b>
          </h5>
          <h5 style="color: white; margin-top: 5%" v-if="totalPrice == 0">
            <b>~ Total price: {{ price }}$ ~</b>
          </h5>
          <h6 style="color: red">
            <b>{{ error }}</b>
          </h6>
        </div>
        <button
          type="button"
          class="btn btn-outline-primary"
          v-on:click="createReservation"
        >
          Create
        </button>
      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios";
import moment from "moment";
export default {
  components: {},
  props: ["cottageId", "date", "persons", "additionalServices", "price"],
  name: "RegisterModal",
  data: function () {
    return {
      addServices:[],
      dateRange: [],
      selectedDateRange: undefined,
      numberOfPersons: 1,
      numOfPersons: undefined,
      totalPrice: 0,
      active: false,
      chosenServices: [],
      availableForPersons: true,
      availableForDateRange: true,
      error: "",
    };
  },
  mounted: function () {
    var element = document.getElementById("logIn-btn");
    element.classList.add("active");
    this.addServices = this.additionalServices;
  },
  beforeUpdate: function () {
    this.addServices = this.additionalServices;
    if (this.date != "undefined") {
      this.dateRange.push(new Date(Date.parse(this.date.split(",")[0])));
      this.dateRange.push(new Date(Date.parse(this.date.split(",")[1])));
    } else {
      if(this.dateRange == null) { this.dateRange = []}
        this.dateRange.push(new Date());
        this.dateRange.push(new Date());
    }
    if (this.persons != "undefined") {
      this.numberOfPersons = this.persons;
    }
  },
  methods: {
    check: function (additionalService) {
      var checkBox = document.getElementById(additionalService.id);
      if (checkBox.checked === true) {
        this.chosenServices.push(additionalService);
      } else {
        const index = this.chosenServices.indexOf(additionalService);
        if (index > -1) {
          this.chosenServices.splice(index, 1);
        }
      }

      this.totalPrice = this.$props.price;
      for (let as of this.chosenServices) {
        this.totalPrice += as.price;
      }
    },
    selectDate: function () {
      if (this.date != undefined) {
        this.selectedDateRange = this.dateRange;
      } else {
        if(this.dateRange == null) { this.dateRange = []}
        this.dateRange.push(new Date());
        this.dateRange.push(new Date());
      }
    },
    changeDateRange: function () {
      if (this.selectedDateRange == null) return;
      axios
        .get(
          "http://localhost:8080/vacationHome/available/dateRange?id=" +
            this.cottageId +
            "&start=" +
            moment(this.selectedDateRange[0]).format(
              "yyyy-MM-DD HH:mm:ss.SSS"
            ) +
            "&end=" +
            moment(this.selectedDateRange[1]).format("yyyy-MM-DD HH:mm:ss.SSS"),
          {
            headers: {
              "Access-Control-Allow-Origin": "http://localhost:8080",
              Authorization: "Bearer " + localStorage.refreshToken,
            },
          }
        )
        .then((response) => {
          this.availableForDateRange = response.data;
          console.log(this.availableForDateRange);
        });
    },
    changePersons: function () {
      this.numOfPersons = parseInt(this.numberOfPersons);
      axios
        .get(
          "http://localhost:8080/vacationHome/available/persons?id=" +
            this.cottageId +
            "&number=" +
            this.numOfPersons,
          {
            headers: {
              "Access-Control-Allow-Origin": "http://localhost:8080",
              Authorization: "Bearer " + localStorage.refreshToken,
            },
          }
        )
        .then((response) => {
          this.availableForPersons = response.data;
          console.log(this.availableForPersons);
        });
    },
    checkPersons: function () {
      axios
        .get(
          "http://localhost:8080/vacationHome/available/persons?id=" +
            this.cottageId +
            "&number=" +
            this.numOfPersons,
          {
            headers: {
              "Access-Control-Allow-Origin": "http://localhost:8080",
              Authorization: "Bearer " + localStorage.refreshToken,
            },
          }
        )
        .then((response) => {
          this.availableForPersons = response.data;
          console.log(this.availableForPersons);
        });
    },
    createReservation: function () {
      if (this.selectedDateRange == undefined) this.selectDate();

      if (this.numOfPersons == undefined) this.changePersons();

      if (this.totalPrice == 0) this.totalPrice = this.price;

      this.error = "";

      if (this.selectedDateRange == null) {
        this.error = "Select date range please";
        return;
      }

      axios
        .get(
          "http://localhost:8080/vacationHome/available/dateRange?id=" +
            this.cottageId +
            "&start=" +
            moment(this.selectedDateRange[0]).format(
              "yyyy-MM-DD HH:mm:ss.SSS"
            ) +
            "&end=" +
            moment(this.selectedDateRange[1]).format("yyyy-MM-DD HH:mm:ss.SSS"),
          {
            headers: {
              "Access-Control-Allow-Origin": "http://localhost:8080",
              Authorization: "Bearer " + localStorage.refreshToken,
            },
          }
        )
        .then((response) => {
          this.availableForDateRange = response.data;
          console.log(this.availableForDateRange);
        })
        .finally(() => {
          this.saveReservation();
        });

      
    },
    saveReservation: function() {
      if (this.availableForPersons && this.availableForDateRange) {
        let reservation = {
          cottageId: this.cottageId,
          startDate: this.selectedDateRange[0],
          endDate: this.selectedDateRange[1],
          persons: this.numOfPersons,
          chosenServices: this.chosenServices,
          price: this.totalPrice,
        };
        console.log(reservation);

        axios
          .post("http://localhost:8080/appointment/new", reservation, {
            headers: {
              "Access-Control-Allow-Origin": "http://localhost:8080",
              Authorization: "Bearer " + localStorage.refreshToken,
            },
          })
          .then(window.location.reload());
      } else {
        if (this.availableForPersons == false)
          this.error = "Not available for " + this.numOfPersons + " persons!";
        else this.error = "Not available for selected date range!";
      }
    },
    closeModal: function () {
      this.mode = "1";
      if (!this.cottage) {
        this.cottageName = "";
        this.cottageDescription = "";
        this.images = [];
        this.street = "";
        this.city = "";
        this.country = "";
        this.rooms = [];
        this.rules = [];
        this.priceList = [];
      }
      let container = document.getElementsByClassName("pac-container")[0];
      if (container) {
        container.remove();
      }
    },
  },
};
</script>

<style scoped src="@/css/newCottageModal.css"></style>
