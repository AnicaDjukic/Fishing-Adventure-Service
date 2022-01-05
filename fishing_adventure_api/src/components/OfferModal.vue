<template>
  <div
    class="modal fade dark"
    id="OfferModal"
    aria-labelledby="OfferModalLabel"
    aria-hidden="true"
    data-bs-backdrop="static"
    data-bs-keyboard="false"
    v-on:show="closeModal"
  >
    <div class="modal-dialog modal-dialog-centered">
      <div class="modal-content" style="height: 43rem">
        <div class="modal-header">
          <h3>New offer</h3>
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
          <p class="discountStatus shadow-none">
            <b class="shadow-none"> {{ discount }}% OFF</b>
          </p>
          <div class="element">
            <h6 style="color: white">Select {{ entityType }}</h6>
            <select id="selectEl" v-on:change="changeSelect">
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
          <h6 style="color: white; margin: 5% 0">Offer will be active:</h6>
          <Datepicker
            style="
              width: 100%;
              margin-right: 10px;
              border: 1px solid white;
              border-radius: 5px;
            "
            dark
            id="picker"
            v-model="dateRange"
            placeholder="Select dates.."
            range
            :enableTimePicker="true"
          ></Datepicker>

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
              type="number"
              v-model="pricePerDay"
              :max="originalPricePerDay"
              min="0"
              class="form-control"
              v-on:change="recalculatePrice"
            />
            <span class="input-group-text" style="width: 4rem">$/day</span>
          </div>

          <h6 style="color: red; margin-top: 1rem">
            <b>{{ error }}</b>
          </h6>
        </div>
        <div class="modal-footer steps-div">
          <button
            type="button"
            class="btn btn-outline-primary"
            v-on:click="createOffer"
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
export default {
  components: {},
  name: "OfferModal",
  data: function () {
    return {
      serviceProfileId: "",
      persons: 1,
      maxPersons: "",
      pricePerDay: "",
      originalPricePerDay: "",
      selectData: "",
      entityType: "",
      discount: 0,
      additionalServices: [],
      dateRange: [],
      chosenServices: [],
      error: "",
    };
  },
  mounted: function () {
    axios
      .get("http://localhost:8080/users/getRole", {
        headers: {
          "Access-Control-Allow-Origin": "http://localhost:8080",
          Authorization: "Bearer " + localStorage.refreshToken,
        },
      })
      .then((res) => {
        let loggedInRole = res.data;

        if (loggedInRole == "ROLE_VACATION_HOME_OWNER") {
          this.entityType = "cottage";
          axios
            .get("http://localhost:8080/vacationHome/getNamesByUser", {
              headers: {
                "Access-Control-Allow-Origin": "http://localhost:8080",
                Authorization: "Bearer " + localStorage.refreshToken,
              },
            })
            .then((res) => {
              this.selectData = res.data;
            });
        } else if (loggedInRole == "ROLE_BOAT_OWNER") {
          this.entityType = "boat";
          axios
            .get("http://localhost:8080/boat/getNamesByUser", {
              headers: {
                "Access-Control-Allow-Origin": "http://localhost:8080",
                Authorization: "Bearer " + localStorage.refreshToken,
              },
            })
            .then((res) => {
              this.selectData = res.data;
            });
        } else {
          window.location.href = "/";
        }
        // else if(loggedInRole == "ROLE_FISHING_INSTRUCTOR"){
        // this.entityType = 'adventure';

        // }
      });
  },
  methods: {
    changeSelect: function () {
      let selectedEntity = document.getElementsByTagName("select")[0].value;
      if (selectedEntity != "") {
        axios
          .get(
            "http://localhost:8080/serviceProfile/getAdditionalServicesByName/" +
              selectedEntity,
            {
              headers: {
                "Access-Control-Allow-Origin": "http://localhost:8080",
                Authorization: "Bearer " + localStorage.refreshToken,
              },
            }
          )
          .then((res) => {
            this.additionalServices = res.data;
          });

        axios
          .get(
            "http://localhost:8080/serviceProfile/getServiceInfoForOfferByName/" +
              selectedEntity,
            {
              headers: {
                "Access-Control-Allow-Origin": "http://localhost:8080",
                Authorization: "Bearer " + localStorage.refreshToken,
              },
            }
          )
          .then((res) => {
            this.pricePerDay = res.data.price;
            this.originalPricePerDay = res.data.price;
            this.maxPersons = res.data.persons;
            this.serviceProfileId = res.data.serviceProfileId;

            if (this.persons > this.maxPersons) {
              this.persons = this.maxPersons;
            }
          });
      } else {
        this.serviceProfileId = "";
        this.persons = 1;
        this.maxPersons = 1;
        this.pricePerDay = "";
        this.originalPricePerDay = "";
        this.discount = 0;
        this.additionalServices = [];
        this.dateRange = [];
        this.chosenServices = [];
        this.error = "";
      }
    },
    check: function (additionalService) {
      var checkBox = document.getElementById(additionalService.id);
      if (checkBox.checked === true) {
        this.chosenServices.push(additionalService);
        this.originalPricePerDay += additionalService.price;
      } else {
        const index = this.chosenServices.indexOf(additionalService);
        this.originalPricePerDay -= additionalService.price;
        if (index > -1) {
          this.chosenServices.splice(index, 1);
        }
      }

      this.recalculatePrice();
    },
    recalculatePrice: function () {
      let percentage = (
        (100 * this.pricePerDay) /
        this.originalPricePerDay
      ).toFixed(2);
      this.discount = (100 - percentage).toFixed(2);
    },
    createOffer: function () {
      if (
        this.dateRange[0] == undefined ||
        document.getElementsByTagName("select")[0].value == ""
      ) {
        this.error = "All fields must be filled.";
      } else {
        this.error = "";
      }

      let offerDto = {
        serviceProfileId: this.serviceProfileId,
        discount: this.discount,
        startDate: this.dateRange[0],
        endDate: this.dateRange[1],
        maxPersons: this.persons,
        price: this.pricePerDay,
        chosenServices: this.chosenServices,
      };
      axios
        .post("http://localhost:8080/appointment/create", offerDto, {
          headers: {
            "Access-Control-Allow-Origin": "http://localhost:8080",
            Authorization: "Bearer " + localStorage.refreshToken,
          },
        })
        .then(() => {
          window.location.reload();
        });
    },
    closeModal: function () {
      this.persons = 1;
      this.maxPersons = 1;
      this.pricePerDay = "";
      this.originalPricePerDay = "";
      this.selectData = "";
      this.entityType = "";
      this.discount = 0;
      this.additionalServices = [];
      this.dateRange = [];
      this.chosenServices = [];
      this.error = "";
    },
  },
};
</script>

<style scoped src="@/css/newCottageModal.css"></style>
