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
          <h3 v-if="mode == '1' && cottageName == ''">New cottage</h3>
          <h3
            v-if="
              (mode === '0') |
                (mode == '1' && cottageName != '') |
                (mode == '2') |
                (mode === '3') |
                (mode === '4') |
                (mode === '5') |
                (mode === '6')
            "
          >
            {{ cottageName }}
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
        <div class="modal-body" v-if="mode == '0'">
          <input
            v-model="cottageName"
            type="text"
            class="login-inputs"
            placeholder="Cottage name"
          />

          <textarea
            style="height: 11rem"
            class="login-inputs-textarea"
            placeholder="Cottage description"
            v-model="cottageDescription"
          />

          <div class="input-group" style="margin-top: 1rem">
            <input
              type="number"
              min="1"
              v-model="pricePerDay"
              class="form-control"
            />
            <span class="input-group-text">$/day</span>
          </div>
          <p style="margin-top: 1rem">
            For updating all other infomation about your cottage, plase click
            here.
          </p>
          <div style="text-align-last: center">
            <button
              class="btn btn-outline-primary"
              style="margin: auto"
              v-on:click="additionalInfo"
            >
              Additional info
            </button>
          </div>
        </div>
        <div class="modal-body" v-if="mode == '1'">
          <h6 style="color: white">Please enter information:</h6>
          <input
            v-model="cottageName"
            type="text"
            class="login-inputs"
            placeholder="Cottage name"
          />

          <textarea
            class="login-inputs-textarea"
            placeholder="Cottage description"
            v-model="cottageDescription"
          />

          <div class="input-group" style="margin-top: 2rem">
            <span class="input-group-text">Persons</span>
            <input
              type="number"
              min="1"
              v-model="persons"
              class="form-control"
            />
          </div>

          <div class="input-group" style="margin-top: 2rem">
            <span class="input-group-text">Cancellation rule</span>
            <input
              type="number"
              min="0"
              step="0.5"
              v-model="cancellationRule"
              class="form-control"
            />
            <span class="input-group-text">%</span>
          </div>
          <label class="error" :id="'cottageNameErr' + cottageId" name="labels">
          </label>
        </div>
        <div class="modal-body" v-if="mode == '2'">
          <cottage-modal-images
            v-on:uploaded="uploaded"
            :files="files"
            :images="images"
          ></cottage-modal-images>
          <label
            class="error"
            :id="'cottageImagesErr' + cottageId"
            name="labels"
          >
          </label>
        </div>
        <div class="modal-body" v-if="mode === '3'">
          <div class="login-title">
            <p :id="'secondErr' + cottageId">
              It is necessary to determine the location of the cottage by
              filling field below. Field must contain street, house number, city
              and coutry.
            </p>
          </div>
          <cottage-modal-map
            :lat="lat"
            :lng="lng"
            v-on:change-address="changeAddress"
          ></cottage-modal-map>
        </div>
        <div class="modal-body" v-if="mode === '4'">
          <cottage-modal-rooms
            :rooms="rooms"
            v-on:roomupdated="roomsUpdated"
          ></cottage-modal-rooms>
          <label class="error" :id="'roomErr' + cottageId" name="labels">
          </label>
        </div>
        <div class="modal-body" v-if="mode === '5'">
          <cottage-modal-rules
            :rules="rules"
            v-on:ruleupdated="rulesUpdated"
          ></cottage-modal-rules>
          <label class="error" :id="'ruleErr' + cottageId" name="labels">
          </label>
        </div>
        <div class="modal-body" v-if="mode === '6'">
          <cottage-modal-price-list
            :priceList="priceList"
            v-on:pricelistupdated="priceListUpdated"
          ></cottage-modal-price-list>
          <label class="error" :id="'priceListErr' + cottageId" name="labels">
          </label>
        </div>
        <div class="modal-footer steps-div">
          <button
            v-on:click="backClick"
            type="button"
            :id="'back-btn' + cottageId"
            style="display: none"
            class="btn btn-outline-primary"
          >
            <i class="fas fa-chevron-left fa-xs"></i> Back
          </button>
          <div
            style="color: white; width: 115px"
            v-if="parseInt(mode) != 7 && parseInt(mode) != 0"
          >
            <i
              class="fa fa-square"
              aria-hidden="true"
              style="margin: 2%"
              v-for="index in parseInt(mode)"
              :key="index"
            ></i>
            <i
              class="fa fa-square-o"
              aria-hidden="true"
              style="margin: 2%"
              v-for="index in 6 - parseInt(mode)"
              :key="index"
            ></i>
          </div>
          <button
            type="button"
            v-if="0 < parseInt(mode) && parseInt(mode) < 6"
            class="btn btn-outline-primary"
            v-on:click="nextClick"
          >
            Next <i class="fas fa-chevron-right fa-xs"></i>
          </button>
          <button
            type="button"
            class="btn btn-outline-primary"
            v-on:click="createCottage"
            v-if="mode == '6' && cottage == undefined"
          >
            Create
          </button>
          <button
            type="button"
            class="btn btn-outline-primary"
            v-on:click="updateCottage"
            v-if="mode == '6' && cottage != undefined"
          >
            Save
          </button>
          <button
            type="button"
            class="btn btn-outline-primary"
            v-on:click="smallUpdate"
            v-if="mode == '0'"
          >
            Save
          </button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios";
axios.defaults.baseURL = process.env.VUE_APP_URL;
import CottageModalImages from "./CottageModalImages.vue";
import CottageModalMap from "./CottageModalMap.vue";
import CottageModalRooms from "./CottageModalRooms.vue";
import CottageModalRules from "./CottageModalRules.vue";
import CottageModalPriceList from "./CottageModalPriceList.vue";
export default {
  components: {
    CottageModalImages,
    CottageModalMap,
    CottageModalRooms,
    CottageModalRules,
    CottageModalPriceList,
  },
  props: ["cottage"],
  name: "RegisterModal",
  data: function () {
    return {
      mode: "1",
      cottageName: "",
      cottageDescription: "",
      images: [],
      street: "",
      city: "",
      country: "",
      postal_code: "",
      rooms: [],
      rules: [],
      priceList: [],
      files: "",
      flagRules: true,
      flagRooms: true,
      flagPriceList: true,
      cottageId: undefined,
      lat: "",
      lng: "",
      persons: "",
      pricePerDay: "",
      date: "",
      cancellationRule: 0.0,
      imageStrings: [],
    };
  },
  mounted: function () {
    var element = document.getElementById("logIn-btn");
    element.classList.add("active");
    if (this.cottage) {
      this.mode = "0";
      this.cottageId = this.cottage.id;
      this.cottageName = this.cottage.name;
      this.cottageDescription = this.cottage.description;
      this.images = this.cottage.images;
      this.street = this.cottage.location.address.street;
      this.city = this.cottage.location.address.city;
      this.country = this.cottage.location.address.country;
      this.postal_code = this.cottage.location.address.zipCode;
      this.rooms = this.cottage.rooms;
      this.rules = this.cottage.rules;
      this.priceList = this.cottage.additionalServices;
      this.lat = this.cottage.location.latitude;
      this.lng = this.cottage.location.longitude;
      this.persons = this.cottage.persons;
      this.cancellationRule = this.cottage.cancellationRule;
      this.pricePerDay = this.cottage.pricePerDay;
      const startDate = new Date(this.cottage.availabilityStart);
      const endDate = new Date(this.cottage.availabilityEnd);
      startDate.setHours(startDate.getHours() - 1);
      endDate.setHours(endDate.getHours() - 1);
      this.date = [startDate, endDate];
    }
  },
  methods: {
    nextClick: function () {
      if (this.mode == "1") {
        if (!this.cottageName || !this.cottageDescription) {
          document.getElementById("cottageNameErr" + this.cottageId).innerHTML =
            "All fields must be filled.";
        } else {
          this.mode = "2";
          document.getElementById("back-btn" + this.cottageId).style.display =
            "block";
          document.getElementById("cottageNameErr" + this.cottageId).innerHTML =
            "";
        }
      } else if (this.mode == "2") {
        if (!this.images && !this.files) {
          document.getElementById(
            "cottageImagesErr" + this.cottageId
          ).innerHTML = "Minimum one image is required.";
        } else {
          this.mode = "3";
          document.getElementById(
            "cottageImagesErr" + this.cottageId
          ).innerHTML = "";
        }
      } else if (this.mode == "3") {
        if (!this.street || !this.city || !this.country) {
          document.getElementById("secondErr" + this.cottageId).style =
            "color: red";
        } else {
          this.mode = "4";
          document.getElementById("secondErr" + this.cottageId).style =
            "color: white";
        }
      } else if (this.mode == "4") {
        if (this.flagRooms) {
          document.getElementById("roomErr" + this.cottageId).innerHTML = "";
          this.mode = "5";
        } else {
          document.getElementById("roomErr" + this.cottageId).innerHTML =
            "All fields must be filled.";
        }
      } else if (this.mode == "5") {
        if (this.flagRules) {
          document.getElementById("ruleErr" + this.cottageId).innerHTML = "";
          this.mode = "6";
        } else {
          document.getElementById("ruleErr" + this.cottageId).innerHTML =
            "All fields must be filled.";
        }
      }
    },
    backClick: function () {
      if (this.mode == "2") {
        this.mode = "1";
        document.getElementById("back-btn" + this.cottageId).style.display =
          "none";
      } else if (this.mode == "3") {
        this.mode = "2";
      } else if (this.mode == "4") {
        this.mode = "3";
      } else if (this.mode == "5") {
        this.mode = "4";
      } else if (this.mode == "6") {
        this.mode = "5";
      }
    },
    closeModal: function () {
      this.mode = "0";
      if (!this.cottage) {
        this.mode = "1";
        this.cottageName = "";
        this.cottageDescription = "";
        this.images = [];
        this.street = "";
        this.city = "";
        this.country = "";
        this.rooms = [];
        this.rules = [];
        this.priceList = [];
        this.cancellationRule = 0;
      }
      let container = document.getElementsByClassName("pac-container")[0];
      if (container) {
        container.remove();
      }
    },
    uploaded: function (files) {
      this.files = files;
      console.log(files);

      for (let aFile of this.files) {
        const file = aFile;
        var reader = new FileReader();
        reader.readAsDataURL(file);
        reader.onload = (e) => {
          this.imageStrings.push(e.target.result.split(",")[1]);
        };
      }
    },
    changeAddress: function (address) {
      if (address == undefined) {
        document.getElementById("secondErr" + this.cottageId).style =
          "color: red;";
      } else {
        document.getElementById("secondErr" + this.cottageId).style =
          "color: white;";
        this.street = address.street;
        this.city = address.city;
        this.postal_code = address.postal_code;
        this.country = address.country;
        this.lng = address.lng;
        this.lat = address.lat;
      }
    },
    roomsUpdated: function (retval) {
      this.flagRooms = retval.result;
      if (retval.result) {
        this.rooms = retval.data;
      }
    },
    rulesUpdated: function (retVal) {
      this.flagRules = retVal.result;
      if (retVal.result) {
        this.rules = retVal.data;
      }
    },
    priceListUpdated: function (retVal) {
      this.flagPriceList = retVal.result;
      if (retVal.result) {
        this.priceList = retVal.data;
      }
    },
    additionalInfo: function () {
      this.mode = "1";
    },
    updateCottage: function () {
      if (!this.flagPriceList) {
        document.getElementById("priceListErr" + this.cottageId).innerHTML =
          "All fields must be filled.";
      } else {
        let additionalServices = [];
        for (let service of this.priceList) {
          additionalServices.push({ name: service.name, price: service.price });
        }

        let rulesFinal = [];
        for (let rule of this.rules) {
          rulesFinal.push({
            content: rule.content,
            isEnforced: rule.isEnforced,
          });
        }

        let roomsFinal = [];
        for (let room of this.rooms) {
          roomsFinal.push({ bedNumber: room.beds });
        }

        let cover = document.coverImage;
        console.log(cover);
        let imageFiles = {
          images: this.imageStrings,
          serviceName: this.cottageName,
          coverImage: cover,
        };
        console.log(imageFiles);

        axios
          .post("/uploadMultipleFiles", imageFiles, {
            headers: {
              "Access-Control-Allow-Origin": process.env.VUE_APP_URL,
              Authorization: "Bearer " + localStorage.refreshToken,
            },
          })
          .then((res) => {
            let home = {
              name: this.cottageName,
              description: this.cottageDescription,
              images: res.data,
              location: {
                longitude: this.lng,
                latitude: this.lat,
                address: {
                  street: this.street,
                  city: this.city,
                  country: this.country,
                  zipCode: this.postal_code,
                },
              },
              rooms: roomsFinal,
              rules: rulesFinal,
              additionalServices: additionalServices,
              persons: this.persons,
              cancellationRule: this.cancellationRule,
            };
            axios
              .put("/vacationHome/update/" + this.cottageId, home, {
                headers: {
                  "Access-Control-Allow-Origin": process.env.VUE_APP_URL,
                  Authorization: "Bearer " + localStorage.refreshToken,
                },
              })
              .then(window.location.reload());
          });
      }
    },
    smallUpdate: function () {
      let dto = {
        pricePerDay: this.pricePerDay,
        name: this.cottageName,
        description: this.cottageDescription,
      };

      axios
        .put("/vacationHome/smallUpdate/" + this.cottageId, dto, {
          headers: {
            "Access-Control-Allow-Origin": process.env.VUE_APP_URL,
            Authorization: "Bearer " + localStorage.refreshToken,
          },
        })
        .then(window.location.reload());
    },
    createCottage: function () {
      if (!this.flagPriceList) {
        document.getElementById("priceListErr" + this.cottageId).innerHTML =
          "All fields must be filled.";
      } else {
        let additionalServices = [];
        for (let service of this.priceList) {
          additionalServices.push({ name: service.name, price: service.price });
        }

        let rulesFinal = [];
        for (let rule of this.rules) {
          rulesFinal.push({
            content: rule.content,
            isEnforced: rule.isEnforced,
          });
        }

        let roomsFinal = [];
        for (let room of this.rooms) {
          roomsFinal.push({ bedNumber: room.beds });
        }

        let cover = document.coverImage;
        console.log(cover);
        let imageFiles = {
          images: this.imageStrings,
          serviceName: this.cottageName,
          coverImage: cover,
        };
        console.log(imageFiles);

        axios
          .post("/uploadMultipleFiles", imageFiles, {
            headers: {
              "Access-Control-Allow-Origin": process.env.VUE_APP_URL,
              Authorization: "Bearer " + localStorage.refreshToken,
            },
          })
          .then((res) => {
            let home = {
              name: this.cottageName,
              description: this.cottageDescription,
              location: {
                longitude: this.lng,
                latitude: this.lat,
                address: {
                  street: this.street,
                  city: this.city,
                  country: this.country,
                  zipCode: this.postal_code,
                },
              },
              rooms: roomsFinal,
              rules: rulesFinal,
              additionalServices: additionalServices,
              persons: this.persons,
              cancellationRule: this.cancellationRule,
              images: res.data,
            };
            axios
              .post("/vacationHome/newHome", home, {
                headers: {
                  "Access-Control-Allow-Origin": process.env.VUE_APP_URL,
                  Authorization: "Bearer " + localStorage.refreshToken,
                },
              })
              .then(window.location.reload());
          });
      }
    },
  },
};
</script>

<style scoped src="@/css/newCottageModal.css"></style>
