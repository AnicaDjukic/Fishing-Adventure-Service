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
          <h6 style="color: white">
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
            v-model="dateRange"
            range
            :enableTimePicker="true"
          ></Datepicker>

          <h6 style="color: white; margin-top: 10%"><b>Persons:</b></h6>
          <input
            type="number"
            min="1"
            v-model="numberOfPersons"
            style="text-align: center"
            class="form-control"
          />

          <h6 style="color: white; margin-top: 10%">
            <b>Select additional services:</b>
          </h6>
          <div id="table-wrapper">
            <div id="table-scroll">
              <table class="table">
                <tbody>
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
                      <label style="color: white">{{
                        additionalService.price
                      }} $</label>
                    </td>
                    <td>
                      <input :id="additionalService.id" type="checkbox"
                      v-on:click="check(additionalService)"/>
                    </td>
                  </tr>
                </tbody>
              </table>
            </div>
          </div>
          <h5 style="color: white; margin-top: 10%" v-if="totalPrice != 0">
            <b>~ Total price: {{ totalPrice }}$ ~</b>
          </h5>
          <h5 style="color: white; margin-top: 10%"  v-if="totalPrice == 0">
            <b>~ Total price: {{ price }}$ ~</b>
          </h5>
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
export default {
  components: {},
  props: ["date", "persons", "additionalServices", "price"],
  name: "RegisterModal",
  data: function () {
    return {
      dateRange: [],
      numberOfPersons: 0,
      totalPrice:0,
      active:false,
      chosenServices:[],
    };
  },
  mounted: function () {
    var element = document.getElementById("logIn-btn");
    element.classList.add("active");
  },
  beforeUpdate: function () {
    this.dateRange.push(new Date(Date.parse(this.date.split(",")[0])));
    this.dateRange.push(new Date(Date.parse(this.date.split(",")[1])));
    this.numberOfPersons = this.persons;
  },
  methods: {
    check: function(additionalService) {
        var checkBox = document.getElementById(additionalService.id);
        if(checkBox.checked === true){
          this.chosenServices.push(additionalService);
        } else {
          const index = this.chosenServices.indexOf(additionalService);
          if (index > -1) {
            this.chosenServices.splice(index, 1);
          }
        }

        this.totalPrice = this.$props.price;
        for(let as of this.chosenServices){
          this.totalPrice += as.price;
        }
    },
    createReservation: function() {
      for(let as of this.additionalServices){
        if(as.checked === true){
          this.chosenServices.push(as.service);
        }
      }
      alert(this.checkServices.length)
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
