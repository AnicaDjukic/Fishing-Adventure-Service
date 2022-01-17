<template>
  <div>
    <GMapAutocomplete
      placeholder="Plase enter address.."
      @place_changed="setPlace"
      style="z-index: 2000; display: none"
      @focus="fixZindex"
      class="login-inputs"
      id="autocompleteInput"
    />
    <input
      placeholder="Plase enter address.."
      type="text"
      class="login-inputs"
      :value="formatted_address"
      id="disabledInput"
      @click="changeAddress"
    />
    <GMapMap :center="center" :zoom="15">
      <GMapMarker
        :key="index"
        v-for="(m, index) in markers"
        :position="m.position"
        @click="center = m.position"
      />
    </GMapMap>
  </div>
</template>

<script>
import axios from "axios";
axios.defaults.baseURL = process.env.VUE_APP_URL;
export default {
  props: ["lng", "lat"],
  data: function () {
    return {
      center: { lat: 45.508, lng: -73.587 },
      currentPlace: null,
      markers: [],
      formatted_address: "",
      selected_place: "",
      street_number: "",
      street: "",
      country: "",
      postal_code: "",
      city: "",
    };
  },
  mounted() {
    document.getElementsByClassName("vue-map")[0].style = "min-height:12rem";
    if (this.lng != undefined && this.lat != undefined) {
      const marker = {
        lat: this.lat,
        lng: this.lng,
      };
      this.markers.push({ position: marker });
      this.center = marker;
    }
    this.getStreetAddressFrom(this.lat, this.lng);
  },
  methods: {
    changeAddress: function () {
      document.getElementById("disabledInput").style = "display: none";
      document.getElementById("autocompleteInput").style = "display: block";
      document.getElementById("autocompleteInput").focus();
    },
    async getStreetAddressFrom(lat, long) {
      try {
        var { data } = await axios.get(
          "https://maps.googleapis.com/maps/api/geocode/json?latlng=" +
            lat +
            "," +
            long +
            "&key=AIzaSyDS-rs0HVP_awX2aP1u49VhJzJcL_K3lko" +
            "&language=en"
        );
        if (data.error_message) {
          console.log(data.error_message);
        } else {
          this.formatted_address = data.results[0].formatted_address;
        }
      } catch (error) {
        console.log(error.message);
      }
    },
    fixZindex: function () {
      document.getElementsByClassName("pac-container")[0].style =
        "z-index:2000";
    },
    setPlace(place) {
      this.currentPlace = place;
      this.addMarker();
    },
    addMarker() {
      this.markers = [];
      this.selected_place = this.currentPlace;
      this.formatted_address = this.currentPlace.formatted_address;
      if (this.currentPlace) {
        const marker = {
          lat: this.currentPlace.geometry.location.lat(),
          lng: this.currentPlace.geometry.location.lng(),
        };
        this.markers.push({ position: marker });
        this.center = marker;
        this.currentPlace = null;
      }
      this.emit();
    },
    emit: function () {
      try {
        for (let component of this.selected_place.address_components) {
          if (component.types[0] == "street_number") {
            this.street_number = component.long_name;
          } else if (component.types[0] == "route") {
            this.street = component.long_name;
          } else if (component.types[0] == "locality") {
            this.city = component.long_name;
          } else if (component.types[0] == "country") {
            this.country = component.long_name;
          } else if (component.types[0] == "postal_code") {
            this.postal_code = component.long_name;
          }

          let address = {
            street: this.street_number + " " + this.street,
            city: this.city,
            postal_code: this.postal_code,
            country: this.country,
            lat: this.selected_place.geometry.location.lat(),
            lng: this.selected_place.geometry.location.lng(),
          };
          this.$emit("change-address", address);
        }
      } catch (err) {
        this.$emit("change-address", undefined);
      }
    },
  },
};
</script>

<style scoped>
.login-inputs {
  display: block;
  margin: 15px auto 0 auto;
  background-color: transparent;
  width: 100%;
  border-width: 0;
  border-bottom-width: 1px;
  border-color: white;
  padding: 2px 20px;
  color: white;
  font-size: 17px;
  margin-bottom: 2rem;
  cursor: pointer;
}

.login-inputs-select:focus {
  border-width: 1px;
}
</style>
