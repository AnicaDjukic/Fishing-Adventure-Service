<template>
  <div
    class="modal fade dark"
    :id="id"
    tabindex="-1"
    aria-labelledby="OfferModalLabel"
    data-bs-backdrop="static"
    aria-hidden="true"
  >
    <div class="modal-dialog modal-dialog-centered">
      <div class="modal-content" style="height: 35.0rem">
        <div class="modal-header">
          <h3 style="font-size: 23px;">
            Complaint responses
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
        <div
          class="modal-body"
        >
       <h4 style="color:white; padding: 0px; text-align: left">To client:</h4>
          <textarea
            class="login-inputs-textarea"
            placeholder="Enter respone to client here.."
            v-model="toClient"
            style="max-height: 10rem; margin-top:15px"
          />
          <h4 style="color:white; margin-top: 15px; text-align: left">To advertiser:</h4>
           <textarea
            class="login-inputs-textarea"
            placeholder="Enter respone to advertiser here.."
            v-model="toAdvertiser"
            style="max-height: 10rem; margin-top:10px"
          />

          <h6 style="color: red; margin-top: 1rem">
            <b>{{ error }}</b>
          </h6>
        </div>
       
        <div class="modal-footer">
          <button
          
            type="button"
            class="btn btn-outline-primary"
            style="width: 30%"
            v-on:click="sendResponse"
          >
            Send response
          </button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios";
export default {
    name: "ComplaintResponseModal",
    props: ['id', 'complaint'],
    data: function () {
        return {
          toClient: "",
          toAdvertiser: "",
          error: "",
        };
    },
    mounted: function () {
    },
    methods: {
        closeModal: function () {
        this.toClient = "";
        this.toAdvertiser = "";
        this.error = "";
      },
      sendResponse: function () {
          if (this.toClient == "" || this.toAdvertiser == "") {
            this.error = "You must write both responses.";
          }
          else {
            let responseDto = {
              complaintId: this.complaint.id,
              clientMessage: this.toClient,
              advertiserMessage: this.toAdvertiser,
              clientEmail: this.complaint.clientEmail,
              advertiserEmail: this.complaint.advertiserEmail,
            };

            axios
                .put(
                "http://localhost:8080/complaint/respondToComplaint/", responseDto,
                {
                    headers: {
                    "Access-Control-Allow-Origin": "http://localhost:8080",
                     Authorization: "Bearer " + localStorage.refreshToken,
                    },
                }
                )
                .then(window.location.reload());
          }
      },
    },
};
</script>


<style scoped src="@/css/newCottageModal.css"></style>
