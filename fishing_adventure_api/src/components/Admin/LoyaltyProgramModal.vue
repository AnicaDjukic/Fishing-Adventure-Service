<template>
  <div
    class="modal fade dark"
    id="loyalty-program"
    tabindex="-1"
    aria-labelledby="LogInModalLabel"
    aria-hidden="true"
  >
    <div class="modal-dialog modal-dialog-centered">
      <div class="modal-content">
        <div class="modal-header" style="border-bottom: 1px solid rgba(218, 165, 32, 0.432);">
          <h3 style="font-size: 23px; margin: 0 0 0 auto; color: white">
            Loyalty program
          </h3>
          <button
            type="button"
            class="btn-close"
            data-bs-dismiss="modal"
            v-on:click="updateLoyaltyProgram"
          >
            <i class="fas fa-times fa-lg"></i>
          </button>
        </div>
        <div
          class="modal-body"
          style="margin:0"
        >
          <div class="reg-request-body" style="color:white">
                <div style="display: inline-flex; justify: space-between; width: 100%; justify-content: space-between;">
                    <span class="left-part-lp">
                        <h4 class="lp-title">Clients</h4>

                        <label for="">Points by payment (%)</label>
                         <input
                            class="input-lp"
                            type="text"
                            v-model="loyaltyProgram.clientPointsPercentage"
                        />


                        <h6 for="" style="margin-top: 40px; font-size: 17px">Points management</h6>

                        <h6 for="" style="margin-top:20px; font-size: 15px; margin-bottom:0; color:rgb(131,137,150)">SILVER</h6>
                        <input
                            style="border: 3px solid rgb(131,137,150); margin-top:5px"
                            class="input-lp"
                            type="text"
                            v-model="loyaltyProgram.clientSilverPoints"
                        />

                        <h6 for="" style="margin-top:10px; font-size: 15px; margin-bottom:0; color:rgb(218 165 32)">GOLD</h6>
                        <input
                            style="border: 3px solid rgb(218 165 32); margin-top:5px"
                            class="input-lp"
                            type="text"
                            v-model="loyaltyProgram.clientGoldPoints"
                        />
                        
                    </span>

                    <span class="right-part-lp">
                        <h4 class="lp-title"> Advertisers</h4>
                        <label for="">Points by payment (%)</label>
                         <input
                            class="input-lp"
                            type="text"
                            v-model="loyaltyProgram.advertiserPointsPercentage"
                        />

                        <h6 for="" style="margin-top: 40px; font-size: 17px">Points management</h6>

                        <h6 for="" style="margin-top:20px; font-size: 15px; margin-bottom:0; color:rgb(131,137,150)">SILVER</h6>
                        <input
                            style="border: 3px solid rgb(131,137,150); margin-top:5px"
                            class="input-lp"
                            type="text"
                            v-model="loyaltyProgram.advertiserSilverPoints"
                        />

                        <h6 for="" style="margin-top:10px; font-size: 15px; margin-bottom:0; color:rgb(218 165 32)">GOLD</h6>
                        <input
                            style="border: 3px solid rgb(218 165 32); margin-top:5px"
                            class="input-lp"
                            type="text"
                            v-model="loyaltyProgram.advertiserGoldPoints"
                        />

                    </span>

                </div>
                
                <p style="text-align: center; margin-top: 10px; font-size 17px; margin-bottom: 5px">Website base cut (%)</p>
                <div style="text-align: center; padding: 0">
                    <input
                    style="margin-top: 2px; margin-bottom: 10px; width: 30%"
                    class="input-lp"
                    type="text"
                    v-model="loyaltyProgram.advertiserBaseCut"
                    />
                </div>
          </div>
        </div>
       
        <div class="modal-footer" style="border-top: 1px solid rgba(218, 165, 32, 0.432);">
          <button
          
            type="button"
            class="btn btn-outline-primary"
            style="width: 20%"
            v-on:click="updateLoyaltyProgram"
          >
            Save
          </button>
          <button
            type="button"
            class="btn btn-outline-primary"
            style="width: 20%"
            v-on:click="reloadLoyaltyProgram"
            data-bs-dismiss="modal"
          >
            Cancel
          </button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios";
axios.defaults.baseURL = process.env.VUE_APP_URL;
export default {
    name: "LoyaltyProgramModal",
    data: function () {
        return {
            loyaltyProgram: [],
        };
    },
    mounted: function () {
        this.reloadLoyaltyProgram();

    },
    methods: {
    updateLoyaltyProgram: function () {
        axios
          .put("/userCategory/updateLoyaltyProgram", this.loyaltyProgram,
            {
              headers: {
                "Access-Control-Allow-Origin": process.env.VUE_APP_URL,
                Authorization: "Bearer " + localStorage.refreshToken,
              },
            }
          )
          .then(() => {
            var myModalEl = document.getElementById('loyalty-program-modal');
            var modal = window.bootstrap.Modal.getInstance(myModalEl);
            modal.hide();
            this.$toast.show("Loyalty program updated.");
        });
       
        },
    reloadLoyaltyProgram: function () {
        axios
          .get("/userCategory/getLoyaltyProgram",
            {
              headers: {
                "Access-Control-Allow-Origin": process.env.VUE_APP_URL,
                Authorization: "Bearer " + localStorage.refreshToken,
              },
            }
          )
          .then((res) => {
            this.loyaltyProgram = res.data;
        });
    },
    },
};
</script>

<style scoped src="@/css/registerModal.css"></style>
<style scoped>
.reg-request-body {
    text-align: left;
}

.left-part-lp {
    width: 50%;
    height: 300px;
    border-right: 1px solid rgba(218, 165, 32, 0.432);
    text-align: center;
}

.left-part-lp label {
   color: white;
}

.right-part-lp {
    height: 300px;
    width: 50%;
    text-align: center;
}

.right-part-lp label {
   color: white;
}

.lp-title {
    font-size: 20px;
}

.input-lp {
    width: 60%;
    margin-top: 5px;
    border: 0;
    border-radius: 5px;
}

</style>
