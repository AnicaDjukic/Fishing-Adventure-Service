<template>
  <div
    class="modal fade dark"
    :id="id"
    tabindex="-1"
    aria-labelledby="LogInModalLabel"
    aria-hidden="true"
  >
    <div class="modal-dialog modal-dialog-centered">
      <div class="modal-content">
        <!-- <div class="modal-header">
          
          <button
            type="button"
            class="btn-close"
            data-bs-dismiss="modal"
            v-on:click="closeModal"
          >
            <i class="fas fa-times fa-lg"></i>
          </button>
        </div> -->
        <div
          class="modal-body"
        >
          <h5 style="margin-bottom:20px"  v-if="user.userType == 'FISHING_INSTRUCTOR'">Fishing instructor <i class="fas fa-fish fa-lg" aria-hidden="true"></i></h5>
          <h5 style="margin-bottom:20px"  v-else-if="user.userType == 'COTTAGE_OWNER'">Cottage owner <i class="fas fa-home fa-lg" aria-hidden="true"></i></h5>
          <h5 style="margin-bottom:20px"  v-else-if="user.userType == 'BOAT_OWNER'">Boat owner <i class="fas fa-anchor fa-lg" aria-hidden="true"></i></h5>
          <div class="reg-request-body" style="color:white">
                <div style="display: inline-flex; justify: space-between; width: 100%; justify-content: space-between;">
                    <div> 
                        <div class="rr-row"> <span class="rr-left"> Email: </span> </div>
                        <div class="rr-row"><span class="rr-left"> Name: </span> </div>
                        <div class="rr-row"><span class="rr-left"> Telephone: </span> </div>
                        <div class="rr-row"><span class="rr-left"> Country: </span></div>
                        <div class="rr-row"><span class="rr-left"> Address: </span> </div>
                    </div>

                    <div>
                        <div class="rr-row"><span class="rr-right"> {{user.email}} </span></div>
                        <div class="rr-row"><span class="rr-right">  {{user.name}}  {{user.surname}} </span></div>
                        <div class="rr-row"><span class="rr-right">  {{user.telephone}} </span></div>
                        <div class="rr-row"><span class="rr-right">  {{user.country}} </span></div>
                        <div class="rr-row"><span class="rr-right">  {{user.street}},  {{user.city}} </span></div>
                    </div>

                </div>
                    <div class="rr-row">Reason for joining:</div>
                <div class="rr-reason-txt">{{user.reason_for_registration}}</div>
          </div>
        </div>
        <div class="modal-body" v-if="mode === 'registerRole'">
         
        </div>
       
        <div class="modal-footer">
          <button
          
            type="button"
            class="btn btn-outline-primary"
            style="width: 20%"
          >
            Approve
          </button>
          <button
            type="button"
            class="btn btn-outline-primary"
            style="width: 20%"
          >
            Reject
          </button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
    name: "RequestForRegistration",
    props: ['id', 'user'],
    data: function () {
        return {
        };
    },
    mounted: function () {

    },
    methods: {
    
    },
};
</script>

<style scoped src="@/css/registerModal.css"></style>
<style scoped>
.reg-request-body {
    text-align: left;
}

.rr-row {
        margin-bottom: 10px;
}

.rr-right {
    background-color: #333b42; 
    padding: 2px 10px;
    border-radius: 10px;
}

.rr-reason-txt {
    background-color: #333b42; 
    padding: 5px 10px;
    text-align: justify;
    border-radius: 10px;
    min-height: fit-content;
    max-height: 150px;
    overflow-y: auto;
}
</style>
