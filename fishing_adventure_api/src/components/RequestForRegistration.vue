<template>
  <div
    class="modal fade dark"
    id="registration-request"
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
          <h5 style="margin-bottom:20px">Fishing instructor <i class="fas fa-fish fa-lg" aria-hidden="true"></i></h5>
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
                        <div class="rr-row"><span class="rr-right"> example@gmail.com </span></div>
                        <div class="rr-row"><span class="rr-right"> Mark Smith </span></div>
                        <div class="rr-row"><span class="rr-right"> 063487923 </span></div>
                        <div class="rr-row"><span class="rr-right"> Serbia </span></div>
                        <div class="rr-row"><span class="rr-right"> Street 2, City </span></div>
                    </div>

                </div>
                    <div class="rr-row">Reason for joining:</div>
                <div class="rr-reason-txt">Lorem ipsum dolor sit amet, consectetur adipiscing elit. Phasellus condimentum ut lectus et efficitur. Quisque finibus facilisis rutrum. 
                    Mauris sed ipsum congue, euismod mauris ac, volutpat mi. 
                    Sed sollicitudin ullamcorper varius. Ut ullamcorper ac ex a suscipit.</div>
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
    props: ['user'],
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
}
</style>
