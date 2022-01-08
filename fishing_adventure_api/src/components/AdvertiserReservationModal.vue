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
          <h3 style="font-size: 23px">New reservation for {{clientName}} {{clientSurname}} </h3>
          <button
            type="button"
            class="btn-close"
            data-bs-dismiss="modal"
          >
            <i class="fas fa-times fa-lg"></i>
          </button>
        </div>
        <div class="modal-body">
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

          <h6 style="color: white; margin: 5% 0">Reservation dates:</h6>

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
            @closed="dateRangeChanged"
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
              v-model="priceField"
              :max="originalTotalPrice"
              min="0"
              class="form-control"
              v-on:change="recalculatePrice"
            />
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
      originalPricePerDay: "",
      selectData: "",
      entityType: "",
      discount: 0,
      additionalServices: [],
      dateRange: [],
      chosenServices: [],
      error: "",
      offerEndDate: undefined,
      originalTotalPrice: "",
    };
  },
  methods: {
    
  },
};
</script>

<style scoped src="@/css/newCottageModal.css"></style>