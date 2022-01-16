<template>
  <div
    class="modal fade dark"
    id="AdvertiserReportModal"
    aria-labelledby="OfferModalLabel"
    aria-hidden="true"
    data-bs-backdrop="static"
    data-bs-keyboard="false"
    v-on:show="closeModal"
  >
    <div class="modal-dialog modal-dialog-centered">
      <div class="modal-content" style="height: 37rem">
        <div class="modal-header">
          <h3 style="font-size: 23px">Reservation report</h3>
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
          <div
            style="
              display: flex;
              justify-content: space-evenly;
              margin-top: 1rem;
            "
          >
            <div class="infoDiv">
              <h6>Reservation name:</h6>
              <div>
                <h6 style="text-align:left">From:</h6>
                <h6 style="text-align:left">To:</h6>
              </div>
              <h6>Client:</h6>
            </div>
            <div class="infoDiv">
              <h6>{{ reportDto.name }}</h6>
              <div>
                <h6>{{ reportDto.startDate }}</h6>
                <h6>{{ reportDto.endDate }}</h6>
              </div>
              <h6 class="clientDiv">
                {{ reportDto.clientName }}{{ reportDto.clientSurname }}
              </h6>
            </div>
          </div>
          <div class="optionsDiv" v-if="selectedOption == undefined">
            <div class="reportOptions" v-on:click="selectOption" id="pleasant">
              Pleasant appointment
            </div>
            <div class="reportOptions" v-on:click="selectOption" id="sanction">
              Request sanction
            </div>
            <div class="reportOptions" v-on:click="selectOption" id="showUp">
              Client didn't show up
            </div>
          </div>
          <div class="textareaDiv" v-if="selectedOption != undefined">
            <div class="selectedOption">
              {{ selectedOption }}
              <button
                type="button"
                class="btn-close"
                v-on:click="deselectOption"
              >
                <i class="fas fa-times fa-lg"></i>
              </button>
            </div>
            <textarea placeholder="Write your report.." v-model="content" />
          </div>
          <h6 style="color: red; margin-top: 1rem">
            <b>{{ error }}</b>
          </h6>
        </div>
        <div class="modal-footer steps-div">
          <button
            type="button"
            class="btn btn-outline-primary"
            v-on:click="createReport"
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
  emits: ["reportEntered"],
  props: ["reportDto"],
  name: "ReportModal",
  data: function () {
    return {
      content: "",
      error: "",
      selectedOption: undefined,
    };
  },
  mounted: function () {},
  methods: {
    selectOption: function (event) {
      this.error = "";
      let elID = event.currentTarget.id;
      document.getElementById("pleasant").classList.remove("active");
      document.getElementById("showUp").classList.remove("active");
      document.getElementById("sanction").classList.remove("active");

      document.getElementById(elID).classList.add("active");
      this.selectedOption = event.target.innerHTML;
    },
    deselectOption: function () {
      this.selectedOption = undefined;
    },
    closeModal: function () {
      this.content = "";
      this.error = "";
      this.selectedOption = undefined;
    },
    createReport: function () {
      if (this.selectedOption == undefined) {
        this.error = "Please select one of the options offerd.";
      } else {
        this.error = "";

        let optionString = "";
        if (this.selectedOption.toLowerCase().includes("show")) {
          optionString = "didntShowUp";
        } else if (this.selectedOption.toLowerCase().includes("sanction")) {
          optionString = "sanctionRequest";
        }

        let report = {
          reservationId: this.reportDto.reservationId,
          content: this.content,
          option: optionString,
        };

        axios
          .post("http://localhost:8080/reservationReport", report, {
            headers: {
              "Access-Control-Allow-Origin": "http://localhost:8080",
              Authorization: "Bearer " + localStorage.refreshToken,
            },
          })
          .then(() => {
            window.location.reload();
            this.$toast.show("Thank you for your entry!");
          });
      }
    },
  },
};
</script>

<style scoped src="@/css/newCottageModal.css"></style>
<style scoped src="@/css/advertiserReport.css"></style>
