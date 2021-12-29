<template>
  <div>
    <GMapAutocomplete
      placeholder="Plase enter address.."
      @place_changed="setPlace"
      style="z-index: 2000"
      @focus="fixZindex"
      class="login-inputs"
    />
    <GMapMap :center="center" :zoom="15" style="width: 100%; margin-top: 20px">
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
export default {
  data: function () {
    return {
      center: { lat: 45.508, lng: -73.587 },
      currentPlace: null,
      markers: [],
      formatted_address: "",
    };
  },
  mounted() {
    document.getElementsByClassName("vue-map")[0].style = "min-height:12rem";
  },
  methods: {
    fixZindex: function () {
      document.getElementsByClassName("pac-container")[0].style =
        "z-index:2000";
    },
    setPlace(place) {
      console.log(place);
      this.currentPlace = place;
      this.addMarker();
    },
    addMarker() {
      this.markers = [];
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
        let parts = this.formatted_address.split(",");
        let city = parts[1].trim().split(" ")[0];
        let postal_code = parts[1].trim().split(" ")[1];
        let address = {
          street: parts[0].trim(),
          city: city.trim(),
          postal_code: postal_code.trim(),
          country: parts[2].trim(),
        };
        console.log(address);
        this.$emit("change-address", address);
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
}

.login-inputs-select:focus {
  border-width: 1px;
}
</style>
