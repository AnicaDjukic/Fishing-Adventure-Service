<template>
  <div
    class="modal fade dark"
    id="AdvertiserReservationModal"
    aria-labelledby="OfferModalLabel"
    aria-hidden="true"
    data-bs-backdrop="static"
    data-bs-keyboard="false"
    v-on:show="closeModal"
  >
    <div class="modal-dialog modal-dialog-centered">
      <div class="modal-content" style="height: 40.5rem">
        <div class="modal-header">
          <h3 style="font-size: 23px">
            New reservation for {{ clientName }} {{ clientSurname }}
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
          <div class="element">
            <h6 style="color: white">Select {{ entityType }}</h6>
            <select id="entitySelectAR" v-on:change="changeSelect">
              <option value=""></option>
              <option
                v-for="data of selectData"
                :value="data.label"
                :key="data.code"
              >
                {{ data.label }}
              </option>
            </select>
          </div>

          <span v-if="entityType != 'adventure'">
            <h6 style="color: white; margin: 5% 0">Reservation dates:</h6>

            <Datepicker
              style="
                width: 100%;
                margin-right: 10px;
                border: 1px solid white;
                border-radius: 5px;
              "
              dark
              v-model="dateRange"
              @closed="dateRangeChanged"
              placeholder="Select dates.."
              range
              :enableTimePicker="true"
            ></Datepicker>
          </span>

          <span v-if="entityType == 'adventure'">
            <h6 style="color: white; margin: 5% 0">Reservation date:</h6>

            <Datepicker
              style="
                width: 100%;
                margin-right: 10px;
                border: 1px solid white;
                border-radius: 5px;
              "
              dark
              v-model="adventureReservationDate"
              @closed="dateRangeChanged"
              placeholder="Select date.."
              :enableTimePicker="true"
            ></Datepicker>
          </span>

          <h6 style="color: white; margin: 5% 0">
            Select additional services:
          </h6>
          <div id="table-wrapper" style="margin-top: -5%">
            <div id="table-scroll">
              <table class="table">
                <tbody>
                  <tr
                    v-if="
                      additionalServices == undefined ||
                      !additionalServices.length
                    "
                    style="color: white"
                  >
                    No additional services available
                  </tr>
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
          <div class="input-group" style="margin-top: 1rem">
            <span class="input-group-text" style="width: 5rem">Persons:</span>
            <input
              type="number"
              v-model="persons"
              :max="maxPersons"
              min="1"
              class="form-control"
            />
          </div>
          <div class="input-group" style="margin-top: 1rem">
            <span class="input-group-text" style="width: 5rem">Price:</span>
            <input
              disabled
              type="number"
              v-model="priceField"
              min="0"
              class="form-control"
              v-on:change="recalculatePrice"
            />
          </div>
          <div
            class="input-group"
            style="margin-top: 1rem"
            v-if="entityType == 'boat'"
          >
            <span class="input-group-text" style="width: 9rem"
              >Owner presence:</span
            >
            <div
              class="form-control"
              style="display: flex; justify-content: space-around; padding: 0"
            >
              <div
                class="ownerPresence"
                v-on:click="changeOwnerPresence"
                id="yesOwnerPresence"
              >
                YES
              </div>
              <div
                class="ownerPresence"
                v-on:click="changeOwnerPresence"
                id="noOwnerPresence"
              >
                NO
              </div>
            </div>
          </div>

          <h6 style="color: red; margin-top: 1rem">
            <b>{{ error }}</b>
          </h6>
        </div>
        <div class="modal-footer steps-div">
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
  </div>
</template>

<script>
import axios from "axios";
axios.defaults.baseURL = process.env.VUE_APP_URL;
import moment from "moment";
export default {
  components: {},
  props: ["clientEmail", "clientName", "clientSurname"],
  name: "OfferModal",
  data: function () {
    return {
      serviceProfileId: "",
      persons: 1,
      maxPersons: "",
      priceField: "",
      pricePerTimeFrame: "",
      selectData: "",
      entityType: "",
      additionalServices: [],
      dateRange: [],
      chosenServices: [],
      error: "",
      originalTotalPrice: "",
      adventureReservationDate: undefined,
      priceForPeriod: 0,
      priceForServices: 0,
      adventureDurationInMins: [],
      available: false,
      ownerPresence: false,
    };
  },
  mounted: function () {
    axios
      .get("/users/getRole", {
        headers: {
          "Access-Control-Allow-Origin": process.env.VUE_APP_URL,
          Authorization: "Bearer " + localStorage.refreshToken,
        },
      })
      .then((res) => {
        let loggedInRole = res.data;
        if (loggedInRole == "ROLE_VACATION_HOME_OWNER") {
          this.entityType = "cottage";
          this.getServiceProfileByHomeOwner();
        } else if (loggedInRole == "ROLE_BOAT_OWNER") {
          this.entityType = "boat";
          this.getServiceProfileByBoatOwner();
        } else if (loggedInRole == "ROLE_FISHING_INSTRUCTOR") {
          this.entityType = "adventure";
          this.getServiceProfileByFishingInstructor();
        }
      });
  },
  methods: {
    getAdditionalServicesByName: function (selectedEntity) {
      axios
        .get(
          "/serviceProfile/getAdditionalServicesByName/" +
            selectedEntity,
          {
            headers: {
              "Access-Control-Allow-Origin": process.env.VUE_APP_URL,
              Authorization: "Bearer " + localStorage.refreshToken,
            },
          }
        )
        .then((res) => {
          this.additionalServices = res.data;
        });
    },
    changeSelect: function () {
      let selectedEntity = document.getElementById("entitySelectAR").value;
      this.priceForPeriod = 0;
      this.priceForServices = 0;
      this.priceField = "";
      this.originalTotalPrice = 0;

      if (selectedEntity != "") {
        this.getAdditionalServicesByName(selectedEntity);
        axios
          .get(
            "/serviceProfile/getServiceInfoForOfferByName/" +
              selectedEntity,
            {
              headers: {
                "Access-Control-Allow-Origin": process.env.VUE_APP_URL,
                Authorization: "Bearer " + localStorage.refreshToken,
              },
            }
          )
          .then((res) => {
            this.pricePerTimeFrame = res.data.price;
            this.originalPricePerDay = res.data.price;
            this.maxPersons = res.data.persons;
            this.serviceProfileId = res.data.serviceProfileId;

            if (this.persons > this.maxPersons) {
              this.persons = this.maxPersons;
            }

            if (this.entityType == "adventure") {
              axios
                .get(
                  "/fishingAdventure/getDurationById/" +
                    this.serviceProfileId,
                  {
                    headers: {
                      "Access-Control-Allow-Origin": process.env.VUE_APP_URL,
                      Authorization: "Bearer " + localStorage.refreshToken,
                    },
                  }
                )
                .then((res) => {
                  this.adventureDurationInMins = res.data;
                });
            }
          });
      } else {
        this.serviceProfileId = "";
        this.persons = 1;
        this.maxPersons = 1;
        this.priceField = "";
        this.pricePerTimeFrame = "";
        this.additionalServices = [];
        this.dateRange = [];
        this.chosenServices = [];
        this.error = "";
        this.adventureReservationDate = undefined;
        this.priceForPeriod = 0;
        this.priceForServices = 0;
        this.originalTotalPrice = 0;
        this.available = false;
      }
      this.dateRangeChanged();
    },
    dateRangeChanged: function () {
      if (this.dateRange[0] != undefined && this.dateRange[1] != undefined) {
        let duration = this.dateRange[1] - this.dateRange[0];
        let days = duration / (1000 * 3600 * 24);
        days = parseInt(days, 10);
        let hours = duration / (1000 * 3600) - days * 24;
        hours = parseInt(hours, 10);
        if (hours > 12) {
          days += 1;
        }
        this.priceForPeriod = days * this.originalPricePerDay;
        this.originalTotalPrice = this.priceForPeriod + this.priceForServices;
        this.priceField = this.originalTotalPrice;

        if (this.entityType == "cottage") {
          axios
            .get(
              "/vacationHome/available/dateRange?id=" +
                this.serviceProfileId +
                "&start=" +
                moment(this.dateRange[0]).format("yyyy-MM-DD HH:mm:ss.SSS") +
                "&end=" +
                moment(this.dateRange[1]).format("yyyy-MM-DD HH:mm:ss.SSS"),
              {
                headers: {
                  "Access-Control-Allow-Origin": process.env.VUE_APP_URL,
                  Authorization: "Bearer " + localStorage.refreshToken,
                },
              }
            )
            .then((res) => {
              if (!res.data) {
                this.error = "Chosen date is not available.";
                this.available = false;
              } else {
                this.available = true;
                this.error = "";
              }
            });
        } else {
          axios
            .get(
              "/boat/available/dateRange?id=" +
                this.serviceProfileId +
                "&start=" +
                moment(this.dateRange[0]).format("yyyy-MM-DD HH:mm:ss.SSS") +
                "&end=" +
                moment(this.dateRange[1]).format("yyyy-MM-DD HH:mm:ss.SSS"),
              {
                headers: {
                  "Access-Control-Allow-Origin": process.env.VUE_APP_URL,
                  Authorization: "Bearer " + localStorage.refreshToken,
                },
              }
            )
            .then((res) => {
              if (!res.data) {
                this.error = "Chosen date is not available.";
                this.available = false;
              } else {
                if (this.ownerPresence) {
                  this.checkBoatOwnerAvailability();
                } else {
                  this.available = true;
                  this.error = "";
                }
              }
            });
        }
      } else if (this.adventureReservationDate != undefined) {
        let startDate = this.adventureReservationDate;
        let endDate = new Date(
          startDate.getTime() + this.adventureDurationInMins * 60000
        );
        axios
          .get(
            "/fishingAdventure/isInstructorAvailable?start=" +
              moment(startDate).format("yyyy-MM-DD HH:mm:ss.SSS") +
              "&end=" +
              moment(endDate).format("yyyy-MM-DD HH:mm:ss.SSS"),
            {
              headers: {
                "Access-Control-Allow-Origin": process.env.VUE_APP_URL,
                Authorization: "Bearer " + localStorage.refreshToken,
              },
            }
          )
          .then((res) => {
            if (!res.data) {
              this.error = "Chosen date is not available.";
              this.available = false;
            } else {
              this.available = true;
              this.error = "";
            }
          });
        this.priceForPeriod = this.pricePerTimeFrame;
        this.originalTotalPrice = this.priceForPeriod + this.priceForServices;
        this.priceField = this.originalTotalPrice;
      }
    },
    changeOwnerPresence: function (event) {
      let checkedPresence = event.target.innerHTML;
      if (checkedPresence.includes("YES")) {
        document.getElementById("noOwnerPresence").classList.remove("active");
        document.getElementById("yesOwnerPresence").classList.add("active");
        this.ownerPresence = true;
        if (this.serviceProfileId != "") {
          this.checkBoatOwnerAvailability();
        }
      } else {
        document.getElementById("yesOwnerPresence").classList.remove("active");
        document.getElementById("noOwnerPresence").classList.add("active");
        this.ownerPresence = false;
      }
      this.dateRangeChanged();
    },
    check: function (additionalService) {
      var checkBox = document.getElementById(additionalService.id);
      if (checkBox.checked === true) {
        this.chosenServices.push(additionalService);
        this.priceForServices += additionalService.price;
      } else {
        const index = this.chosenServices.indexOf(additionalService);
        this.priceForServices -= additionalService.price;
        if (index > -1) {
          this.chosenServices.splice(index, 1);
        }
      }
      this.originalTotalPrice = this.priceForPeriod + this.priceForServices;
      this.priceField = this.originalTotalPrice;
    },
    createReservation: function () {
      if (!this.available) {
        if (this.entityType != "boat") {
          this.error = "Chosen date is not available.";
        }
      } else if (this.maxPersons < this.persons) {
        this.error = "Maximum number of people is " + this.maxPersons + ".";
      } else {
        let startDate = [];
        let endDate = [];
        let ownerPresence = [];
        if (this.entityType == "adventure") {
          startDate = this.adventureReservationDate;
          ownerPresence = true;
          endDate = new Date(
            startDate.getTime() + this.adventureDurationInMins * 60000
          );
        } else {
          startDate = this.dateRange[0];
          endDate = this.dateRange[1];
          if (this.entityType == "cottage") {
            ownerPresence = true;
          } else {
            ownerPresence = this.ownerPresence;
          }
        }

        let reservation = {
          serviceId: this.serviceProfileId,
          startDate: startDate,
          endDate: endDate,
          persons: this.persons,
          chosenServices: this.chosenServices,
          price: this.priceField,
          ownersPresence: ownerPresence,
          clientEmail: this.clientEmail,
        };

        axios
          .post(
            "/reservation/newByAdvertiser",
            reservation,
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
    closeModal: function () {
      this.persons = 1;
      this.maxPersons = 1;
      this.priceField = "";
      this.pricePerTimeFrame = "";
      this.entityType = "";
      this.additionalServices = [];
      this.dateRange = [];
      this.chosenServices = [];
      this.error = "";
      this.adventureReservationDate = undefined;
      this.priceForPeriod = 0;
      this.priceForServices = 0;
      this.originalTotalPrice = 0;
      this.available = false;
      document.getElementById("entitySelectAR").value = "";
    },
    getServiceProfileByHomeOwner: function () {
      axios
        .get("/vacationHome/getNamesByUser", {
          headers: {
            "Access-Control-Allow-Origin": process.env.VUE_APP_URL,
            Authorization: "Bearer " + localStorage.refreshToken,
          },
        })
        .then((res) => {
          this.selectData = res.data;
        });
    },
    getServiceProfileByBoatOwner: function () {
      axios
        .get("/boat/getNamesByUser", {
          headers: {
            "Access-Control-Allow-Origin": process.env.VUE_APP_URL,
            Authorization: "Bearer " + localStorage.refreshToken,
          },
        })
        .then((res) => {
          document.getElementById("noOwnerPresence").classList.add("active");
          this.selectData = res.data;
        });
    },
    getServiceProfileByFishingInstructor: function () {
      axios
        .get("/fishingAdventure/getNamesByUser", {
          headers: {
            "Access-Control-Allow-Origin": process.env.VUE_APP_URL,
            Authorization: "Bearer " + localStorage.refreshToken,
          },
        })
        .then((res) => {
          this.selectData = res.data;
        });
    },
    checkBoatOwnerAvailability: function () {
      axios
        .get(
          "/boatOwner/available/dateRange?id=" +
            this.serviceProfileId +
            "&start=" +
            moment(this.dateRange[0]).format("yyyy-MM-DD HH:mm:ss.SSS") +
            "&end=" +
            moment(this.dateRange[1]).format("yyyy-MM-DD HH:mm:ss.SSS"),
          {
            headers: {
              "Access-Control-Allow-Origin": process.env.VUE_APP_URL,
              Authorization: "Bearer " + localStorage.refreshToken,
            },
          }
        )
        .then((res) => {
          if (!res.data) {
            this.error = "Boat owner is not available.";
            this.available = false;
          } else {
            this.available = true;
            this.error = "";
          }
        });
    },
  },
};
</script>

<style scoped src="@/css/newCottageModal.css"></style>
