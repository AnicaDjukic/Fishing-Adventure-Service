<template>
  <div>
    <div class="card mb-3 bg-dark mt-3" style="width: 65%; margin: auto">
      <div class="row g-0" v-on:click="open">
        <div class="col-md-4 shadow-none">
          <img
            style="width: 100%; height: 225px; object-fit: cover"
            :src="require('@/assets/' + subscription.imagePath)"
            class="img-fluid rounded-start shadow-none"
          />
        </div>

        <div class="col-md-8 shadow-none" name="main-col">
          <div class="card-body shadow-none">
            <div class="card-text shadow-none" style="display: flex">
              <h5 class="card-title shadow-none">{{subscription.name}}</h5>
              <!--<p class="advertiserTitle shadow-none">@marinaGibson</p>-->
              <p
                class="top-right-corner shadow-none"
                v-on:click="unsubscribe"
              >
                <button
                  class="btn btn-primary shadow-none mb-2"
                  style="
                    background-color: rgb(94 23 30);
                    border-color: rgb(94 23 30);
                  "
                >
                  Unsubscribe
                </button>
              </p>
              
            </div>
            <div class="card-text shadow-none" style="display: flex">
              <div class="shadow-none">
                <p class="card-text text-left shadow-none mb-1">
                  {{subscription.description}}
                </p>
              </div>
              <p
                class="shadow-none"
                style="
                  margin: 0;
                  text-align: right;
                  margin-left: auto;
                  color: #c79e2c;
                  width: 30%;
                "
              >
                <i class="fas fa-star shadow-none"> {{subscription.rating}}</i>
              </p>
            </div>
            <div class="card-text fw-bold shadow-none" style="display: flex">
              <p class="shadow-none" style="margin: 0">
                {{subscription.location.address.street}}, {{subscription.location.address.city}}, {{subscription.location.address.country}}
              </p>
              <p
                class="shadow-none"
                style="
                  margin: 0;
                  text-align: right;
                  margin-left: auto;
                  font-size: x-large;
                "
              >
              </p>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios";
export default {
  props: ["subscription"],
  data: function () {
    return {
      founded: false,
    };
  },
  mounted: function () {
    
  },
  methods: {
    unsubscribe: function (event) {
      event.preventDefault();
      event.stopPropagation();
      axios
        .get("http://localhost:8080/client/unsubscribe/" + this.subscription.id, {
          headers: {
            "Access-Control-Allow-Origin": "http://localhost:8080",
            Authorization: "Bearer " + localStorage.refreshToken,
          },
        })
        .then();

      this.$nextTick(() => {
          this.$emit('refresh');
      });
    },
    open: function () {
      axios
      .get("http://localhost:8080/vacationHome/exists/" + this.subscription.id, {
        headers: {
          "Access-Control-Allow-Origin": "http://localhost:8080",
          Authorization: "Bearer " + localStorage.refreshToken,
        },
      })
      .then((res) => {
        this.founded = res.data;
        if(this.founded == true){
          window.location.href = "/cottage/?id=" + this.subscription.id + "&date=undefined&persons=undefined";
        } else {
          this.findBoat();
        }
      });
    },
    findBoat: function() {
      axios
      .get("http://localhost:8080/boat/exists/" + this.subscription.id,
      {
        headers: {
          "Access-Control-Allow-Origin": "http://localhost:8080",
          Authorization: "Bearer " + localStorage.refreshToken,
        },
      })
      .then((res) => {
        this.founded = res.data;
        if(this.founded == true){
          window.location.href = "/boat/?id=" + this.subscription.id;
        } else {
          window.location.href = "/fishingAdventure";
        }
      });
    }
  },
};
</script>
<style scoped>
.img-fluid {
  height: 100%;
}
h5 {
  color: white;
}
p {
  color: gray;
}
@media (min-width: 768px) {
  .col-md-4 {
    flex: 0 0 auto;
    width: 25%;
  }
}
@media (min-width: 768px) {
  .col-md-8 {
    flex: 0 0 auto;
    width: 74.666667%;
  }
}
div {
  text-align: left;
}
.row {
  align-items: center;
}
.card :hover {
  cursor: pointer;
  box-shadow: 2px 2px 15px 2px rgba(187, 178, 178, 0.664);
  transition: 0.5s;
}
.advertiserTitle {
  background-color: rgba(128, 128, 128, 0.226);
  color: gray;
  padding: 2px;
  border-radius: 5px;
  margin-left: 3%;
}
.advertiserTitle:hover {
  box-shadow: 3px 5px 0px 0px rgb(22 20 20 / 66%) !important;
  transition: 0.5s;
}
.top-right-corner {
  margin: 0;
  text-align: right;
  margin-left: auto;
  color: #832626;
}
.top-right-corner i:hover {
  box-shadow: 3px 5px 0px 0px rgb(22 20 20 / 66%) !important;
  transition: 0.5s;
}
</style>