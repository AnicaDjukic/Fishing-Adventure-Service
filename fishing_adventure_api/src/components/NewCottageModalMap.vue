<template>
  <div>
    <form>
      <input
        v-model="street"
        id="street"
        type="text"
        class="login-inputs"
        placeholder="House number and street"
        v-on:change="emit"
      />
      <input
        v-model="city"
        id="city"
        type="text"
        class="login-inputs"
        placeholder="City"
        v-on:change="emit"
      />
      <input
        v-model="country"
        id="country"
        type="text"
        class="login-inputs"
        placeholder="Country"
        v-on:change="emit"
      />

      <div id="map"></div>
    </form>
  </div>
</template>

<script>
export default {
  props: ["st", "ci", "co"],
  data: function () {
    return {
      street: "",
      city: "",
      country: "",
    };
  },
  mounted() {
    this.street = this.st;
    this.country = this.co;
    this.city = this.ci;
  },
  methods: {
    emit: function () {
      let address = {
        street: this.street,
        city: this.city,
        country: this.country,
      };
      this.$emit("change-address", address);
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
}

.login-inputs-select {
  display: block;
  margin: 15px auto 0 auto;
  background-color: transparent;
  width: 58%;
  border-width: 0;
  border-bottom-width: 1px;
  border-color: white;
  padding: 2px 16px;
  color: white;
  font-size: 17px;
}

.login-inputs-select option {
  background-color: rgba(44, 53, 63, 1);
  color: white;
}

.login-inputs-select:focus {
  border-width: 1px;
}
</style>
