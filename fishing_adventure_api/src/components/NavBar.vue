<template>
  <div>
    <nav class="navbar sticky-top navbar-expand-lg navbar-dark bg-dark">
      <div class="container-fluid">
        <a class="navbar-brand" href="/">
          <img src="../assets/logo_white.png" alt="" width="150" height="50" />
        </a>
        <button
          class="navbar-toggler"
          type="button"
          data-bs-toggle="collapse"
          data-bs-target="#navbarScroll"
          aria-controls="navbarScroll"
          aria-expanded="false"
          aria-label="Toggle navigation"
        >
          <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarScroll">
          <ul class="navbar-nav me-auto my-2 my-lg-0 navbar-nav-scroll">
            <li class="nav-item">
              <a class="nav-link" aria-current="page" href="/">Home</a>
            </li>

            <li class="nav-item dropdown">
              <a
                class="nav-link dropdown-toggle"
                href="#"
                id="navbarScrollingDropdown"
                role="button"
                data-bs-toggle="dropdown"
                aria-expanded="false"
                v-if="loggedInUser != 'administrator'"
              >
                Explore
              </a>
              <a
                class="nav-link dropdown-toggle"
                href="#"
                id="navbarScrollingDropdown"
                role="button"
                data-bs-toggle="dropdown"
                aria-expanded="false"
                v-else
              >
                Services
              </a>
              <ul
                class="dropdown-menu text-center dropdown-menu-dark"
                aria-labelledby="navbarScrollingDropdown"
              >
                <li><a class="dropdown-item" href="/search/boats">Boats</a></li>
                <li>
                  <a class="dropdown-item" href="/search/cottages">Cottages</a>
                </li>
                <li>
                  <a class="dropdown-item" href="/search/adventures"
                    >Adventures</a
                  >
                </li>
              </ul>
            </li>

             <li class="nav-item dropdown">
              <a
                class="nav-link dropdown-toggle"
                href="#"
                id="navbarScrollingDropdown"
                role="button"
                data-bs-toggle="dropdown"
                aria-expanded="false"
                v-if="loggedInUser == 'ROLE_CLIENT'"
              >
                Reservations
              </a>
              <ul
                class="dropdown-menu text-center dropdown-menu-dark"
                aria-labelledby="navbarScrollingDropdown"
              >
                <li><a class="dropdown-item" href="/client/pastReservations">Past reservations</a></li>
                <li>
                  <a class="dropdown-item" href="/client/currentReservations">Current reservations</a>
                </li>
              </ul>
            </li>

            <li class="nav-item" v-if="loggedInUser == 'ROLE_CLIENT'">
              <a class="nav-link" aria-current="page" href="/client/subscriptions">Subscriptions</a>
            </li>


            <li class="nav-item dropdown" v-if="loggedInUser == 'homeOwner'">
              <a
                class="nav-link dropdown-toggle"
                href="#"
                id="navbarScrollingDropdown"
                role="button"
                data-bs-toggle="dropdown"
                aria-expanded="false"
              >
                My advertisments
              </a>
              <ul
                class="dropdown-menu text-center dropdown-menu-dark"
                aria-labelledby="navbarScrollingDropdown"
              >
                <li><a class="dropdown-item" href="/cottages">Cottages</a></li>
                <li><a class="dropdown-item" href="/calendar">Calander</a></li>
                <li>
                  <a class="dropdown-item" href="/specialOffers"
                    >Special Offers</a
                  >
                </li>
                <li>
                  <a class="dropdown-item" href="/reservations">Reservations</a>
                </li>
              </ul>
            </li>
            <li class="nav-item dropdown" v-if="loggedInUser == 'administrator'">
              <a
                class="nav-link dropdown-toggle"
                href="#"
                id="navbarScrollingDropdown"
                role="button"
                data-bs-toggle="dropdown"
                aria-expanded="false"
              >
                Users
              </a>
              <ul
                class="dropdown-menu text-center dropdown-menu-dark"
                aria-labelledby="navbarScrollingDropdown"
              >
                <li><a class="dropdown-item" href="/registrationRequests">Registration Requests</a></li>
                <li><a class="dropdown-item" href="/users">All Users</a></li>
              </ul>
            </li>
          </ul>
          <div class="d-flex" v-if="loggedInUser == undefined">
            <button
              type="button"
              class="btn btn-outline-primary"
              data-bs-toggle="modal"
              data-bs-target="#LogInModal"
            >
              Register / Log In
            </button>
          </div>

          <div class="d-flex" v-if="loggedInUser != undefined">
            <button
              type="button"
              class="btn btn-outline-primary me-2"
              v-on:click="openProfile"
            >
              Log out
            </button>
            <button
              type="button"
              class="btn btn-outline-primary"
              v-on:click="openProfile"
            >
              <i class="fas fa-user"></i>
            </button>
          </div>
        </div>
      </div>
    </nav>
  </div>
  <register-modal></register-modal>
</template>

<script>
import RegisterModal from "@/components/RegisterModal.vue";

export default {
  components: { "register-modal": RegisterModal },
  name: "NavBar",
  data: function () {
    return {
      loggedInUser: undefined,
    };
  },
  mounted: function () {
    this.loggedInUser = localStorage.role;
  },
  methods: {
    openProfile: function () {
      window.location.href = "/profile";
    },
  },
};
</script>

<style lang="scss" scoped></style>
