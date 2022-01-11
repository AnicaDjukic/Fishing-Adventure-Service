<template>
  <div
    class="modal fade dark"
    id="NewBoatModal"
    aria-labelledby="NewBoatModalLabel"
    aria-hidden="true"
    data-bs-backdrop="static"
    data-bs-keyboard="false"
    v-on:show="closeModal"
  >
    <div class="modal-dialog modal-dialog-centered">
      <div class="modal-content">
        <div class="modal-header">
          <h3 v-if="mode == '1' && boatName == ''">New boat</h3>
          <h3
            v-if="
              (mode === '0') |
                (mode == '1' && boatName != '') |
                (mode == '2') |
                (mode === '3') |
                (mode === '4') |
                (mode === '5') |
                (mode === '6') |
                (mode === '7')
            "
          >
            {{ boatName }}
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
            v-model="boatName"
            type="text"
            class="login-inputs"
            placeholder="Boat name"
          />

          <textarea
            style="height: 11rem"
            class="login-inputs-textarea"
            placeholder="Boat description"
            v-model="boatDescription"
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
          <p style="margin-top: 5px">
            For updating all other infomation about your boat, plase click here.
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
            v-model="boatName"
            type="text"
            class="login-inputs"
            placeholder="Boat name"
          />

          <textarea
            class="login-inputs-textarea"
            placeholder="Boat description"
            v-model="boatDescription"
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
          <label class="error" :id="'boatNameErr' + boatId" name="labels">
          </label>
        </div>
        <div class="modal-body" v-if="mode == '2'">
          <boat-modal-boat-info
            :length="length"
            :motorNumber="motorNumber"
            :maxSpeed="maxSpeed"
            :motorPower="motorPower"
            :type="type"
            v-on:infoupdated="infoUpdate"
          ></boat-modal-boat-info>
          <label class="error" :id="'boatInfoErr' + boatId" name="labels">
          </label>
        </div>
        <div class="modal-body" v-if="mode == '3'">
          <boat-modal-images
            v-on:uploaded="uploaded"
            :files="files"
            :images="images"
          ></boat-modal-images>
          <label class="error" :id="'boatImagesErr' + boatId" name="labels">
          </label>
        </div>
        <div class="modal-body" v-if="mode === '4'">
          <div class="login-title">
            <p :id="'secondErr' + boatId">
              It is necessary to determine the location of the boat by filling
              field below. Field must contain street, house number, city and
              coutry.
            </p>
          </div>
          <boat-modal-map
            :lat="lat"
            :lng="lng"
            v-on:change-address="changeAddress"
          ></boat-modal-map>
        </div>
        <div class="modal-body" v-if="mode === '5'">
          <boat-modal-fishing-eq
            :equipment="equipment"
            v-on:equipupdated="equipUpdated"
          ></boat-modal-fishing-eq>
          <label class="error" :id="'equipErr' + boatId" name="labels"> </label>
        </div>
        <div class="modal-body" v-if="mode === '6'">
          <boat-modal-navigation-eq
            :navEquipment="navEquipment"
            v-on:navequipupdated="navEquipUpdated"
          ></boat-modal-navigation-eq>
          <label class="error" :id="'navEquipErr' + boatId" name="labels">
          </label>
        </div>
        <div class="modal-body" v-if="mode === '7'">
          <boat-modal-rules
            :rules="rules"
            v-on:ruleupdated="rulesUpdated"
          ></boat-modal-rules>
          <label class="error" :id="'ruleErr' + boatId" name="labels"> </label>
        </div>
        <div class="modal-body" v-if="mode === '8'">
          <boat-modal-price-list
            :priceList="priceList"
            v-on:pricelistupdated="priceListUpdated"
          ></boat-modal-price-list>
          <label class="error" :id="'priceListErr' + boatId" name="labels">
          </label>
        </div>
        <div class="modal-footer steps-div">
          <button
            v-on:click="backClick"
            type="button"
            :id="'back-btn' + boatId"
            style="display: none"
            class="btn btn-outline-primary"
          >
            <i class="fas fa-chevron-left fa-xs"></i> Back
          </button>
          <div
            style="color: white; width: 11rem"
            v-if="parseInt(mode) != 9 && parseInt(mode) != 0"
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
              v-for="index in 8 - parseInt(mode)"
              :key="index"
            ></i>
          </div>
          <button
            type="button"
            v-if="0 < parseInt(mode) && parseInt(mode) < 8"
            class="btn btn-outline-primary"
            v-on:click="nextClick"
          >
            Next <i class="fas fa-chevron-right fa-xs"></i>
          </button>
          <button
            type="button"
            class="btn btn-outline-primary"
            v-on:click="createBoat"
            v-if="mode == '8' && boat == undefined"
          >
            Create
          </button>
          <button
            type="button"
            class="btn btn-outline-primary"
            v-on:click="updateBoat"
            v-if="mode == '8' && boat != undefined"
          >
            Save
          </button>
          <button
            type="button"
            class="btn btn-outline-primary"
            v-on:click="updatePrice"
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
import BoatModalImages from "./CottageModalImages.vue";
import BoatModalMap from "./CottageModalMap.vue";
import BoatModalRules from "./CottageModalRules.vue";
import BoatModalPriceList from "./CottageModalPriceList.vue";
import BoatModalFishingEq from "./BoatModalFishingEq.vue";
import BoatModalNavigationEq from "./BoatModalNavigationEq.vue";
import BoatModalBoatInfo from "./BoatModalBoatInfo.vue";
export default {
  components: {
    BoatModalImages,
    BoatModalMap,
    BoatModalRules,
    BoatModalPriceList,
    BoatModalFishingEq,
    BoatModalNavigationEq,
    BoatModalBoatInfo,
  },
  props: ["boat"],
  data: function () {
    return {
      mode: "1",
      boatName: "",
      boatDescription: "",
      images: [],
      street: "",
      city: "",
      country: "",
      postal_code: "",
      rules: [],
      priceList: [],
      files: "",
      flagRules: true,
      flagPriceList: true,
      flagEquipment: true,
      flagNavEquipment: true,
      boatId: undefined,
      lat: "",
      lng: "",
      persons: "",
      pricePerDay: "",
      date: "",
      equipment: [],
      navEquipment: [],
      type: "",
      length: "",
      motorNumber: "",
      motorPower: "",
      maxSpeed: "",
    };
  },
  mounted: function () {
    if (this.boat) {
      this.mode = "0";
      this.type = this.boat.type;
      this.length = this.boat.length;
      this.motorNumber = this.boat.motorNumber;
      this.motorPower = this.boat.motorPower;
      this.maxSpeed = this.boat.maxSpeed;
      this.equipment = this.boat.fishingEquipments;
      this.navEquipment = this.boat.navigationEquipments;
      this.boatId = this.boat.id;
      this.boatName = this.boat.name;
      this.boatDescription = this.boat.description;
      this.images = this.boat.images;
      this.street = this.boat.location.address.street;
      this.city = this.boat.location.address.city;
      this.country = this.boat.location.address.country;
      this.postal_code = this.boat.location.address.zipCode;
      this.rules = this.boat.rules;
      this.priceList = this.boat.additionalServices;
      this.lat = this.boat.location.latitude;
      this.lng = this.boat.location.longitude;
      this.persons = this.boat.persons;
      this.pricePerDay = this.boat.pricePerDay;
      const startDate = new Date(this.boat.availabilityStart);
      const endDate = new Date(this.boat.availabilityEnd);
      startDate.setHours(startDate.getHours() - 1);
      endDate.setHours(endDate.getHours() - 1);
      this.date = [startDate, endDate];
    }
  },
  methods: {
    nextClick: function () {
      if (this.mode == "1") {
        if (!this.boatName || !this.boatDescription) {
          document.getElementById("boatNameErr" + this.boatId).innerHTML =
            "All fields must be filled.";
        } else {
          this.mode = "2";
          document.getElementById("back-btn" + this.boatId).style.display =
            "block";
          document.getElementById("boatNameErr" + this.boatId).innerHTML = "";
        }
      } else if (this.mode == "2") {
        if (
          !this.length ||
          !this.maxSpeed ||
          !this.motorNumber ||
          !this.motorPower
        ) {
          document.getElementById("boatInfoErr" + this.boatId).innerHTML =
            "All fields must be filled.";
        } else {
          this.mode = "3";
          document.getElementById("boatInfoErr" + this.boatId).innerHTML = "";
        }
      } else if (this.mode == "3") {
        if (!this.images && !this.files) {
          document.getElementById("boatImagesErr" + this.boatId).innerHTML =
            "Minimum one image is required.";
        } else {
          this.mode = "4";
          document.getElementById("boatImagesErr" + this.boatId).innerHTML = "";
        }
      } else if (this.mode == "4") {
        if (!this.street || !this.city || !this.country) {
          document.getElementById("secondErr" + this.boatId).style =
            "color: red";
        } else {
          this.mode = "5";
          document.getElementById("secondErr" + this.boatId).style =
            "color: white";
        }
      } else if (this.mode == "5") {
        if (this.flagEquipment) {
          document.getElementById("equipErr" + this.boatId).innerHTML = "";
          this.mode = "6";
        } else {
          document.getElementById("equipErr" + this.boatId).innerHTML =
            "All fields must be filled.";
        }
      } else if (this.mode == "6") {
        if (this.flagNavEquipment) {
          document.getElementById("navEquipErr" + this.boatId).innerHTML = "";
          this.mode = "7";
        } else {
          document.getElementById("navEquipErr" + this.boatId).innerHTML =
            "All fields must be filled.";
        }
      } else if (this.mode == "7") {
        if (this.flagRules) {
          document.getElementById("ruleErr" + this.boatId).innerHTML = "";
          this.mode = "8";
        } else {
          document.getElementById("ruleErr" + this.boatId).innerHTML =
            "All fields must be filled.";
        }
      }
    },
    backClick: function () {
      if (this.mode == "2") {
        this.mode = "1";
        document.getElementById("back-btn" + this.boatId).style.display =
          "none";
      } else if (this.mode == "3") {
        this.mode = "2";
      } else if (this.mode == "4") {
        this.mode = "3";
      } else if (this.mode == "5") {
        this.mode = "4";
      } else if (this.mode == "6") {
        this.mode = "5";
      } else if (this.mode == "7") {
        this.mode = "6";
      } else if (this.mode == "8") {
        this.mode = "7";
      }
    },
    closeModal: function () {
      this.mode = "0";
      if (!this.boat) {
        this.lat = "";
        this.lng = "";
        this.persons = "";
        this.pricePerDay = "";
        this.date = "";
        this.type = "";
        this.length = "";
        this.motorNumber = "";
        this.motorPower = "";
        this.maxSpeed = "";
        this.files = [];
        this.flagRules = true;
        this.flagPriceList = true;
        this.flagEquipment = true;
        this.flagNavEquipment = true;
        this.flagInfo = true;
        this.boatId = undefined;
        this.mode = "1";
        this.boatName = "";
        this.boatDescription = "";
        this.images = [];
        this.street = "";
        this.city = "";
        this.country = "";
        this.postal_code = "";
        this.equipment = [];
        this.navEquipment = [];
        this.rules = [];
        this.priceList = [];
      }
      let container = document.getElementsByClassName("pac-container")[0];
      if (container) {
        container.remove();
      }
    },

    uploaded: function (files) {
      this.files = files;
    },
    changeAddress: function (address) {
      if (address == undefined) {
        document.getElementById("secondErr" + this.boatId).style =
          "color: red;";
      } else {
        document.getElementById("secondErr" + this.boatId).style =
          "color: white;";
        this.street = address.street;
        this.city = address.city;
        this.postal_code = address.postal_code;
        this.country = address.country;
        this.lng = address.lng;
        this.lat = address.lat;
      }
    },
    equipUpdated: function (retval) {
      this.flagEquipment = retval.result;
      if (retval.result) {
        this.equipment = retval.data;
      }
    },
    navEquipUpdated: function (retval) {
      this.flagNavEquipment = retval.result;
      if (retval.result) {
        this.navEquipment = retval.data;
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
    infoUpdate: function (retVal) {
      this.type = retVal.type;
      this.length = retVal.length;
      this.motorNumber = retVal.motorNumber;
      this.motorPower = retVal.motorPower;
      this.maxSpeed = retVal.maxSpeed;
    },
    updateBoat: function () {
      if (!this.flagPriceList) {
        document.getElementById("priceListErr" + this.boatId).innerHTML =
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

        let fishingEqFinal = [];
        for (let equip of this.equipment) {
          fishingEqFinal.push({ name: equip.name });
        }

        let navigationEqFinal = [];
        for (let equip of this.navEquipment) {
          navigationEqFinal.push({ name: equip.name });
        }
        let boat = {
          type: this.type,
          length: this.length,
          motorNumber: this.motorNumber,
          motorPower: this.motorPower,
          maxSpeed: this.maxSpeed,
          name: this.boatName,
          description: this.boatDescription,
          images: null,
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
          fishingEquipments: fishingEqFinal,
          navigationEquipments: navigationEqFinal,
          rules: rulesFinal,
          additionalServices: additionalServices,
          persons: this.persons,
        };

        axios
          .put("http://localhost:8080/boat/update/" + this.boatId, boat, {
            headers: {
              "Access-Control-Allow-Origin": "http://localhost:8080",
              Authorization: "Bearer " + localStorage.refreshToken,
            },
          })
          .then(window.location.reload());
      }
    },
    updatePrice: function () {
      let dto = {
        pricePerDay: this.pricePerDay,
        name: this.boatName,
        description: this.boatDescription,
      };

      axios
        .put("http://localhost:8080/boat/smallUpdate/" + this.boatId, dto, {
          headers: {
            "Access-Control-Allow-Origin": "http://localhost:8080",
            Authorization: "Bearer " + localStorage.refreshToken,
          },
        })
        .then(window.location.reload());
    },
    createBoat: function () {
      if (!this.flagPriceList) {
        document.getElementById("priceListErr" + this.boatId).innerHTML =
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

        let fishingEqFinal = [];
        for (let equip of this.equipment) {
          fishingEqFinal.push({ name: equip.name });
        }

        let navigationEqFinal = [];
        for (let equip of this.navEquipment) {
          navigationEqFinal.push({ name: equip.name });
        }

        let boat = {
          type: this.type,
          length: this.length,
          motorNumber: this.motorNumber,
          motorPower: this.motorPower,
          maxSpeed: this.maxSpeed,
          name: this.boatName,
          description: this.boatDescription,
          images: null,
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
          fishingEquipments: fishingEqFinal,
          navigationEquipments: navigationEqFinal,
          rules: rulesFinal,
          additionalServices: additionalServices,
          persons: this.persons,
        };

        axios
          .post("http://localhost:8080/boat/newBoat", boat, {
            headers: {
              "Access-Control-Allow-Origin": "http://localhost:8080",
              Authorization: "Bearer " + localStorage.refreshToken,
            },
          })
          .then(window.location.reload());
      }
    },
  },
};
</script>

<style scoped src="@/css/newCottageModal.css"></style>
