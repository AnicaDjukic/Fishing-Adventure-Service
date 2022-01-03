<template>
  <div>
    <h6>Plase enter information:</h6>

    <div class="input-group" style="margin-top: 2rem">
      <span class="input-group-text">Boat type</span>
      <input
        type="text"
        v-model="typeB"
        class="form-control"
        v-on:change="emit"
      />
    </div>

    <div class="input-group" style="margin-top: 2rem">
      <span class="input-group-text">Boat length</span>
      <input
        type="number"
        min="1"
        v-model="lengthB"
        class="form-control"
        v-on:change="emit"
      />
    </div>

    <div class="input-group" style="margin-top: 2rem">
      <span class="input-group-text">Motor Power</span>
      <input
        type="number"
        min="1"
        v-model="motorPowerB"
        class="form-control"
        v-on:change="emit"
      />
    </div>
    <div class="input-group" style="margin-top: 2rem">
      <span class="input-group-text">Motor Number</span>
      <input
        type="number"
        min="1"
        v-model="motorNumberB"
        class="form-control"
        v-on:change="emit"
      />
    </div>
    <div class="input-group" style="margin-top: 2rem">
      <span class="input-group-text">Max speed</span>
      <input
        type="number"
        min="1"
        v-model="maxSpeedB"
        class="form-control"
        v-on:change="emit"
      />
    </div>
  </div>
</template>

<script>
export default {
  props: ["type", "length", "motorNumber", "motorPower", "maxSpeed"],
  name: "NewBoatModalBoatInfo",
  data: function () {
    return {
      typeB: "",
      lengthB: "",
      motorNumberB: "",
      motorPowerB: "",
      maxSpeedB: "",
    };
  },
  mounted() {
    if (this.type) {
      this.typeB = this.type;
      this.lengthB = this.length;
      this.motorNumberB = this.motorNumber;
      this.motorPowerB = this.motorPower;
      this.maxSpeedB = this.maxSpeed;
    }
  },
  methods: {
    emit: function () {
      let retVal = {
        type: this.typeB,
        length: this.lengthB,
        motorNumber: this.motorNumberB,
        motorPower: this.motorPowerB,
        maxSpeed: this.maxSpeedB,
      };
      this.$emit("infoupdated", retVal);
    },
  },
};
</script>

<style scoped>
.form-control {
  background-color: #393c3f73;
  color: white;
}

.form-control:focus {
  box-shadow: 0 0 0 0.2rem rgba(255, 255, 255, 0.432);
}

.form-floating > label {
  color: gray;
}

.input-group-text {
  width: 8rem;
  background-color: #393c3f73;
  color: gray;
}
</style>
