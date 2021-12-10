<template>
  <div>
    <div class="title">
      <h1>My past reservations</h1>
      <i
        class="fas fa-id-card fa-3x"
        style="font-family: 'Font Awesome 5 Pro'"
      ></i>
    </div>
    <div style="background-color: #212529; padding: 15px">
      <div class="container w-100" style="flex-direction: column">
        <div
          class="row row-cols-1 row-cols-sm-1 row-cols-md-4"
          style="justify-content: space-evenly; align-items: center"
        >
          <div class="col-md-3">
            <button type="button" class="btn btn-outline-primary text-nowrap">
              New reservation
            </button>
          </div>
          <div class="col-md-3">
            <input
              class="form-control me-2"
              type="search"
              placeholder="Search"
              aria-label="Search"
            />
          </div>
          <div class="col-md-4">
            <Datepicker
              style="
                width: 100%;
                margin-right: 10px;
                border: 1px solid white;
                border-radius: 5px;
              "
              dark
              id="picker"
              v-model="date"
              range
              placeholder="Select date"
              :enableTimePicker="false"
            ></Datepicker>
          </div>
          <div class="col-md-2">
            <select
              class="form-select form-select-dark form-select-sm text-white bg-dark"
              aria-label=".form-select-sm example"
            >
              <option selected>All reservations</option>
              <option value="1">Boats</option>
              <option value="2">Cottages</option>
              <option value="3">Adventures</option>
            </select>
          </div>
        </div>
      </div>
    </div>
    <div style="margin-top: 5%">
      <ClientReservationCard
        :review="false"
        v-for="index in 5"
        :key="index"
      ></ClientReservationCard>
    </div>
  </div>
</template>

<script>
import ClientReservationCard from "@/components/ClientReservationCard.vue";
export default {
  components: { ClientReservationCard },
  data: function () {
    return {
      numberOfPersons: "",
      range: {
        start: new Date(2020, 9, 12),
        end: new Date(2020, 9, 16),
      },
      date: "",
    };
  },
  methods: {},
};
</script>

<style scoped src="@/css/mycottages.css"></style>
