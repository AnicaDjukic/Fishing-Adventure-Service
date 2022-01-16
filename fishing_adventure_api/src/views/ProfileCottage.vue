<template>
  <div class="fa-page">
    <div class="main-img-fa">
      <img src="@/assets/c16.jpg" alt="" />
      <div class="tagline-fa" v-if="loggedInRole == 'ROLE_CLIENT'">
        <h2>
          Enjoy in your cottage <br />
          today!
        </h2>
        <button
          data-bs-toggle="modal"
          :data-bs-target="'#cottage'"
          class="book-btn"
        >
          Book a cottage
        </button>
      </div>
      <div class="tagline-subscribe-fa" v-if="loggedInRole == 'ROLE_CLIENT'">
        <button class="subscribe-btn" v-if="!subscribed" v-on:click="subscribe">
          <i class="fas fa-bell" style="margin-right: 1.1rem"></i> Subscribe
        </button>
        <button
          class="unsubscribe-btn"
          v-if="subscribed"
          v-on:click="unsubscribe"
        >
          <i class="far fa-bell-slash" style="margin-right: 1.1rem"></i>
          Unsubscribe
        </button>
      </div>
    </div>
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark navbar-info">
      <div class="container-fluid">
        <button
          class="navbar-toggler"
          type="button"
          data-bs-toggle="collapse"
          data-bs-target="#navbarText"
          aria-controls="navbarText"
          aria-expanded="false"
          aria-label="Toggle navigation"
        >
          <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarText">
          <ul class="navbar-nav me-auto mb-2 mb-lg-0 menu-ol-fa">
            <li
              v-on:click="changeMenuDisplay"
              id="about-fa"
              style="border-bottom: 1px solid white"
            >
              About
            </li>
            <li v-on:click="changeMenuDisplay" id="ex-info-fa">
              Extra Information
            </li>
            <li v-on:click="changeMenuDisplay" id="loc-fa">Location</li>
            <li v-on:click="changeMenuDisplay" id="pl-fa">Pricelist</li>
          </ul>
        </div>
      </div>
    </nav>
    <div class="menu-fa">
      <div class="menu-body-fa">
        <div class="menu-about-fa" style="text-align: justify">
          <div class="ma-top-part">
            <h3>{{ entity.name }}</h3>
            <h5>For up to {{ entity.persons }} people</h5>
          </div>
          <p>{{ entity.description }}</p>
        </div>

        <div class="menu-ex-info-fa" style="display: none">
          <div class="ei-bonus">
            <h4>Rooms:</h4>
            <table class="table table-striped">
              <tbody>
                <tr v-for="(room, index) in entity.rooms" :key="room.id">
                  <td class="as-title" style="color: white">
                    Room {{ index }}
                  </td>
                  <td>
                    <span class="as-price"
                      >{{ room.bedNumber }} <i class="fas fa-bed"></i
                    ></span>
                  </td>
                </tr>
              </tbody>

              <tbody></tbody>
            </table>
          </div>

          <div class="ei-rules">
            <h4>Rulebook:</h4>
            <ul>
              <li v-for="rule in entity.rules" :key="rule.id">
                <i
                  v-if="rule.isEnforced == true"
                  class="far fa-check-circle"
                  style="color: green; margin-right: 2%"
                ></i>

                <i
                  v-if="rule.isEnforced == false"
                  class="far fa-times-circle"
                  style="color: red; margin-right: 2%"
                ></i>
                {{ rule.content }}
              </li>
            </ul>
          </div>
        </div>

        <div class="menu-loc-fa" style="display: none">
          <div class="loc-info">
            <h5>{{ address.street }}</h5>
            <h5>{{ address.city }}</h5>
            <h5>{{ location.latitude }}, {{ location.longitude }}</h5>
          </div>

          <div class="map-fa">
            <GMapMap
              :center="center"
              :zoom="13"
              :options="{
                zoomControl: true,
                mapTypeControl: false,
                scaleControl: true,
                streetViewControl: false,
                rotateControl: true,
                fullscreenControl: true,
              }"
              map-type-id="terrain"
              style="width: 100%; height: 150px"
            >
              <GMapCluster>
                <GMapMarker
                  :key="index"
                  v-for="(m, index) in markers"
                  :position="m.position"
                  :clickable="false"
                  :draggable="false"
                  @click="center = m.position"
                />
              </GMapCluster>
            </GMapMap>
          </div>
        </div>

        <div class="menu-pl-fa" style="display: none">
          <h5>Additional services:</h5>
          <table class="table table-striped">
            <tbody>
              <tr
                v-for="service in entity.additionalServices"
                :key="service.id"
              >
                <td class="as-title">{{ service.name }}</td>
                <td>
                  <span class="as-price">${{ service.price }}</span>
                </td>
              </tr>
            </tbody>

            <tbody></tbody>
          </table>
        </div>
      </div>
    </div>

    <div class="bottom-part-fa">
      <div class="pa-title-fa">
        <h2>Interior and exterior</h2>
      </div>
      <div
        id="carouselExampleIndicators"
        class="carousel slide"
        data-bs-ride="carousel"
        style="
          padding: 0% 7%;
          background: #212529;
          height: 450px;
          width: 75%;
          margin: 20px auto 0 auto;
        "
      >
        <div class="carousel-indicators">
          <button
            v-for="(image, index) in entity.images"
            :key="image.id"
            :class="{ active: index === counter }"
            type="button"
            data-bs-target="#carouselExampleIndicators"
            :data-bs-slide-to="index"
            aria-current="true"
            :aria-label="'Slide ' + index"
          ></button>
        </div>
        <div class="carousel-inner">
          <div
            class="carousel-item"
            :class="{ active: index === counter }"
            v-for="(image, index) in entity.images"
            :key="image.id"
          >
            <img
              :src="require('@/assets/' + image.path)"
              class="d-block w-100"
              alt="..."
              style="object-fit: contain; height: 450px"
            />
          </div>
        </div>
        <button
          class="carousel-control-prev"
          type="button"
          data-bs-target="#carouselExampleIndicators"
          data-bs-slide="prev"
          style="width: 7%"
        >
          <span class="carousel-control-prev-icon" aria-hidden="true"></span>
          <span class="visually-hidden">Previous</span>
        </button>
        <button
          class="carousel-control-next"
          type="button"
          data-bs-target="#carouselExampleIndicators"
          data-bs-slide="next"
          style="width: 7%"
        >
          <span class="carousel-control-next-icon" aria-hidden="true"></span>
          <span class="visually-hidden">Next</span>
        </button>
      </div>

      <div class="special-offers-fa">
        <div class="so-title-fa">
          <h2 style="margin-top:10px">Special Offers</h2>
        </div>
         <p v-if="offers.length == 0" style="font-size: 24px">There are no special offers currently</p>
      </div>

      <OffersCardNoImage
        v-for="offer of offers"
        :key="offer.offerId"
        :offer="offer"
        :loggedInRole="loggedInRole"
        :entityType="entityType"
        v-on:refresh="refresh"
      ></OffersCardNoImage>

      <div class="special-offers-fa">
        <div class="so-title-fa">
          <h2>Past experiences</h2>
        </div>
        <Review
         v-for="review of reviews"
         :key="review.id"
         :review="review"
        ></Review>

      </div>
    </div>
  </div>
  <ReservationModal
    :id="'cottage'"
    v-bind:serviceId="entity.id"
    v-bind:date="date"
    v-bind:persons="persons"
    v-bind:additionalServices="entity.additionalServices"
    v-bind:price="entity.pricePerDay"
    v-if="loggedInRole == 'ROLE_CLIENT'"
  ></ReservationModal>
</template>

<script>
import axios from "axios";
import ReservationModal from "@/components/Modals/ReservationModal.vue";
import OffersCardNoImage from "@/components/OfferCards/OffersCardNoImage.vue";
import Review from "@/components/Utils/Review.vue";
export default {
  components: { ReservationModal, OffersCardNoImage, Review },
  data: function () {
    return {
      offers: "",
      entityType: "home",
      reviews: "",
      subscribed: false,
      loggedInRole: "",
      date: [],
      persons: 1,
      entity: "",
      address: "",
      location: "",
      counter: 0,
      center: { lat: 0, lng: 0 },
      markers: [
        {
          position: {
            lat: 0,
            lng: 0,
          },
        },
      ],
    };
  },
  mounted() {
    window.scrollTo(0, 0);

    axios
      .get("http://localhost:8080/vacationHome/" + this.$route.query.id, {
        headers: {
          "Access-Control-Allow-Origin": "http://localhost:8080",
          Authorization: "Bearer " + localStorage.refreshToken,
        },
      })
      .then((response) => {
        this.entity = response.data;
        this.address = response.data.location.address;
        this.location = response.data.location;
        (this.center.lat = response.data.location.latitude),
          (this.center.lng = response.data.location.longitude),
          (this.markers[0].position.lat = response.data.location.latitude);
        this.markers[0].position.lng = response.data.location.longitude;
      })
      .finally(() => {
        this.isSubscribed();
      });

    if (this.$route.query.id != undefined) {
      this.date = this.$route.query.date;
      this.persons = this.$route.query.persons;
    }
    axios
      .get(
        "http://localhost:8080/appointment/getOffersByServiceId/" +
          this.$route.query.id,
        {
          headers: {
            "Access-Control-Allow-Origin": "http://localhost:8080",
            Authorization: "Bearer " + localStorage.refreshToken,
          },
        }
      )
      .then((response) => {
        this.offers = response.data;
      });

    axios
      .get(
        "http://localhost:8080/review/getReviewsForService/" +
          this.$route.query.id,
        {
          headers: {
            "Access-Control-Allow-Origin": "http://localhost:8080",
            Authorization: "Bearer " + localStorage.refreshToken,
          },
        }
      )
      .then((response) => {
        this.reviews = response.data;
      });
  },
  methods: {
    refresh: function (reserved) {
      const index = this.offers.indexOf(reserved);
        if (index > -1) {
          this.offers.splice(index, 1);
        }
    },
    isSubscribed: function () {
      axios
        .get("http://localhost:8080/users/getRole", {
          headers: {
            "Access-Control-Allow-Origin": "http://localhost:8080",
            Authorization: "Bearer " + localStorage.refreshToken,
          },
        })
        .then((res) => {
          this.loggedInRole = res.data;
        })
        .finally(() => {
          if (this.loggedInRole == "ROLE_CLIENT") {
            axios
              .get(
                "http://localhost:8080/client/isSubscribed/" + this.entity.id,
                {
                  headers: {
                    "Access-Control-Allow-Origin": "http://localhost:8080",
                    Authorization: "Bearer " + localStorage.refreshToken,
                  },
                }
              )
              .then((res) => {
                this.subscribed = res.data;
              });
          }
        });
    },
    changeMenuDisplay: function (event) {
      let elID = event.currentTarget.id;
      document.getElementById("about-fa").style.borderBottom = "0px";
      document.getElementById("ex-info-fa").style.borderBottom = "0px";
      document.getElementById("loc-fa").style.borderBottom = "0px";
      document.getElementById("pl-fa").style.borderBottom = "0px";

      document.getElementById(elID).style.borderBottom = "1px solid white";

      if (elID == "about-fa") {
        document.querySelector(".menu-about-fa").style.display = "block";
        document.querySelector(".menu-ex-info-fa").style.display = "none";
        document.querySelector(".menu-loc-fa").style.display = "none";
        document.querySelector(".menu-pl-fa").style.display = "none";
      } else if (elID == "ex-info-fa") {
        document.querySelector(".menu-about-fa").style.display = "none";
        document.querySelector(".menu-ex-info-fa").style.display = "flex";
        document.querySelector(".menu-loc-fa").style.display = "none";
        document.querySelector(".menu-pl-fa").style.display = "none";
      } else if (elID == "loc-fa") {
        document.querySelector(".menu-about-fa").style.display = "none";
        document.querySelector(".menu-ex-info-fa").style.display = "none";
        document.querySelector(".menu-loc-fa").style.display = "inline-flex";
        document.querySelector(".menu-pl-fa").style.display = "none";
      } else if (elID == "pl-fa") {
        document.querySelector(".menu-about-fa").style.display = "none";
        document.querySelector(".menu-ex-info-fa").style.display = "none";
        document.querySelector(".menu-loc-fa").style.display = "none";
        document.querySelector(".menu-pl-fa").style.display = "block";
      } else {
        document.querySelector(".menu-about-fa").style.display = "none";
        document.querySelector(".menu-ex-info-fa").style.display = "none";
        document.querySelector(".menu-loc-fa").style.display = "none";
        document.querySelector(".menu-pl-fa").style.display = "none";
      }
    },
    subscribe: function () {
      axios
        .get("http://localhost:8080/client/subscribe/" + this.entity.id, {
          headers: {
            "Access-Control-Allow-Origin": "http://localhost:8080",
            Authorization: "Bearer " + localStorage.refreshToken,
          },
        })
        .then((res) => {
          if (res.data == true) this.subscribed = true;
        });
    },
    unsubscribe: function () {
      axios
        .get("http://localhost:8080/client/unsubscribe/" + this.entity.id, {
          headers: {
            "Access-Control-Allow-Origin": "http://localhost:8080",
            Authorization: "Bearer " + localStorage.refreshToken,
          },
        })
        .then((res) => {
          if (res.data == true) this.subscribed = false;
        });
    },
  },
};
</script>

<style scoped src="@/css/fishingAdventure.css"></style>
