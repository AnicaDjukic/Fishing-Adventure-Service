<template>
  <div class="fa-page">
    <div class="main-img-fa">
      <img src="@/assets/fa7.jpg" alt="" />
      <div class="tagline-fa" v-if="loggedInRole == 'ROLE_CLIENT'">
        <h2>
          Start your adventure <br />
          today!
        </h2>
        <button
          class="book-btn"
          data-bs-toggle="modal"
          :data-bs-target="'#adventure'"
        >
          Book an adventure
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
            <li v-on:click="changeMenuDisplay" id="instr-fa">Instructor</li>
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
          <p>
            {{ entity.description }}
          </p>
        </div>

        <div class="menu-ex-info-fa" style="display: none">
          <div class="ei-bonus">
            <h4>With every reservation you will get:</h4>
            <ul
              v-for="fishingEq in entity.fishingEquipment"
              :key="fishingEq.id"
            >
              <li>
                <i
                  class="fas fa-circle ei-circle"
                  style="margin-bottom: 2px"
                ></i>
                {{ fishingEq.name }}
              </li>
            </ul>
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

          <h6 style="margin-top: 5%">
            *In case of cancelation the instructor keeps 20% of the full payment
            including bought additional services
          </h6>
        </div>

        <div class="menu-instr-fa" style="display: none">
          <h4>{{ instructorFullName }}</h4>
          <p>
            {{ instructorBiography }}
          </p>
        </div>
      </div>
    </div>

    <div class="bottom-part-fa">
      <div class="pa-title-fa">
        <h2>Previous adventures</h2>
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
          <h2>Special Offers</h2>
        </div>
        <p style="font-size: 24px">There are no special offers currently</p>
      </div>

      <div class="special-offers-fa">
        <div class="so-title-fa">
          <h2>Past experiences</h2>
        </div>

        <div class="review-fa continer">
          <div class="review-tp">
            <img class="user-rev-img" src="@/assets/fa7.jpg" alt="" />
            <div class="rev-info">
              <h5>John Smith</h5>
              <h6>01.12.2021.</h6>
            </div>
            <div
              style="
                margin-left: auto;
                display: inline-flex;
                justify-content: flex-end;
                align-content: center;
              "
            >
              <i class="fas fa-star rev-star"></i>
              <i class="fas fa-star rev-star"></i>
              <i class="fas fa-star rev-star"></i>
              <i class="fas fa-star rev-star"></i>
            </div>
          </div>
          <p>
            Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do
            eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim
            ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut
            aliquip ex ea commodo consequat. Duis aute irure dolor in
            reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla
            pariatur.
          </p>
        </div>
      </div>
    </div>
  </div>
  <ReservationModal
    :id="'adventure'"
    v-bind:duration="entity.duration"
    v-bind:serviceId="entity.id"
    v-bind:date="date"
    v-bind:persons="persons"
    v-bind:additionalServices="entity.additionalServices"
    v-bind:price="entity.price"
    v-if="loggedInRole == 'ROLE_CLIENT'"
  ></ReservationModal>
</template>

<script>
import axios from "axios";
import ReservationModal from "@/components/Modals/ReservationModal.vue";
export default {
  components: { ReservationModal },
  name: "FishingAdventure",
  data() {
    return {
      offers: "",
      subscribed: false,
      loggedInRole: "",
      date: [],
      persons: 1,
      entity: "",
      address: "",
      location: "",
      counter: 0,
      instructorFullName: "",
      instructorBiography: "",
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
      .get("http://localhost:8080/fishingAdventure/" + this.$route.query.id, {
        headers: {
          "Access-Control-Allow-Origin": "http://localhost:8080",
          Authorization: "Bearer " + localStorage.refreshToken,
        },
      })
      .then((response) => {
        this.entity = response.data;
        this.location = response.data.location;
        this.address = this.location.address;
        this.instructorFullName =
          response.data.fishingInstructor.name +
          " " +
          response.data.fishingInstructor.surname;
        this.instructorBiography = response.data.fishingInstructor.biography;
        (this.center.lat = this.location.latitude),
          (this.center.lng = this.location.longitude),
          (this.markers[0].position.lat = this.location.latitude);
        this.markers[0].position.lng = this.location.longitude;
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
  },
  methods: {
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
      document.getElementById("instr-fa").style.borderBottom = "0px";

      document.getElementById(elID).style.borderBottom = "1px solid white";

      if (elID == "about-fa") {
        document.querySelector(".menu-about-fa").style.display = "block";
        document.querySelector(".menu-ex-info-fa").style.display = "none";
        document.querySelector(".menu-loc-fa").style.display = "none";
        document.querySelector(".menu-pl-fa").style.display = "none";
        document.querySelector(".menu-instr-fa").style.display = "none";
      } else if (elID == "ex-info-fa") {
        document.querySelector(".menu-about-fa").style.display = "none";
        document.querySelector(".menu-ex-info-fa").style.display = "flex";
        document.querySelector(".menu-loc-fa").style.display = "none";
        document.querySelector(".menu-pl-fa").style.display = "none";
        document.querySelector(".menu-instr-fa").style.display = "none";
      } else if (elID == "loc-fa") {
        document.querySelector(".menu-about-fa").style.display = "none";
        document.querySelector(".menu-ex-info-fa").style.display = "none";
        document.querySelector(".menu-loc-fa").style.display = "inline-flex";
        document.querySelector(".menu-pl-fa").style.display = "none";
        document.querySelector(".menu-instr-fa").style.display = "none";
      } else if (elID == "pl-fa") {
        document.querySelector(".menu-about-fa").style.display = "none";
        document.querySelector(".menu-ex-info-fa").style.display = "none";
        document.querySelector(".menu-loc-fa").style.display = "none";
        document.querySelector(".menu-pl-fa").style.display = "block";
        document.querySelector(".menu-instr-fa").style.display = "none";
      } else {
        document.querySelector(".menu-about-fa").style.display = "none";
        document.querySelector(".menu-ex-info-fa").style.display = "none";
        document.querySelector(".menu-loc-fa").style.display = "none";
        document.querySelector(".menu-pl-fa").style.display = "none";
        document.querySelector(".menu-instr-fa").style.display = "block";
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
