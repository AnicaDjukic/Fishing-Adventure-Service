<template>
  <div
    class="modal fade dark"
    id="LogInModal"
    tabindex="-1"
    aria-labelledby="LogInModalLabel"
    aria-hidden="true"
  >
    <div class="modal-dialog modal-dialog-centered">
      <div class="modal-content">
        <div class="modal-header">
          <ul class="nav nav-tabs">
            <li class="nav-item">
              <a
                class="nav-link"
                aria-current="page"
                href="#"
                id="logIn-btn"
                v-on:click="logInClick"
                >Log in</a
              >
            </li>
            <li class="nav-item">
              <a
                class="nav-link"
                id="register-btn"
                href="#"
                v-on:click="registerClick"
                >Register</a
              >
            </li>
          </ul>
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
          v-if="mode === 'registerCustomer' || mode === 'registerAdvertiser'"
        >
          <h5>Please enter your information</h5>
          <form>
            <input
              type="text"
              class="login-inputs"
              placeholder="E-mail"
              v-model="user.email"
            />

            <div class="row">
              <input
                type="text"
                class="col login-inputs"
                placeholder="Name"
                v-model="user.name"
              />

              <input
                type="text"
                class="col login-inputs"
                placeholder="Surname"
                v-model="user.surname"
              />
            </div>
            <input
              type="text"
              class="login-inputs"
              placeholder="Address"
              v-model="user.street"
            />
            <div class="row">
              <input
                type="text"
                class="col login-inputs"
                placeholder="City"
                v-model="user.city"
              />
              <input
                type="text"
                class="col login-inputs"
                placeholder="Country"
                v-model="user.country"
              />
            </div>
            <input
              type="text"
              class="login-inputs"
              placeholder="Phone number"
              v-model="user.phoneNumber"
            />

            <input
              type="password"
              class="login-inputs"
              placeholder="Enter password"
              v-model="user.password1"
            />
            <input
              type="password"
              class="login-inputs"
              placeholder="Enter password again"
              v-model="user.password2"
            />

            <label
              class="error"
              id="emptyFieldsError"
              name="labels"
              display="hidden"
            >
            </label>
          </form>
        </div>
        <div class="modal-body" v-if="mode === 'logIn'">
          <form>
            <input
              type="text"
              class="login-inputs"
              placeholder="E-mail"
              v-model="user.email"
            />

            <input
              type="password"
              class="login-inputs"
              placeholder="Password"
              v-model="user.password1"
            />

            <label
              class="error"
              id="emptyFieldsError"
              name="labels"
              display="hidden"
            >
            </label>
          </form>
        </div>
        <div class="modal-body" v-if="mode === 'registerRole'">
          <h6>Choose your role on our site:</h6>
          <div class="btn-group" role="role">
            <input
              type="radio"
              class="btn-check"
              name="btnradio"
              id="customer-radio-btn"
              autocomplete="off"
              value="customer"
              v-bind:checked="chosenRole == 'customer'"
            />
            <label class="btn btn-outline-primary" for="customer-radio-btn"
              ><i class="fas fa-user fa-lg"></i><br />
              Customer</label
            >

            <input
              type="radio"
              class="btn-check"
              name="btnradio"
              id="boat-radio-btn"
              autocomplete="off"
              value="boat"
              v-bind:checked="chosenRole == 'boat'"
            />
            <label class="btn btn-outline-primary" for="boat-radio-btn"
              ><i class="fas fa-anchor fa-lg"></i><br />
              Boat owner</label
            >

            <input
              type="radio"
              class="btn-check"
              name="btnradio"
              id="home-radio-btn"
              autocomplete="off"
              value="home"
              v-bind:checked="chosenRole == 'home'"
            />
            <label class="btn btn-outline-primary" for="home-radio-btn"
              ><i class="fas fa-home fa-lg"></i><br />
              Home owner</label
            >

            <input
              type="radio"
              class="btn-check"
              name="btnradio"
              id="instructor-radio-btn"
              autocomplete="off"
              value="instructor"
              v-bind:checked="chosenRole == 'instructor'"
            />
            <label class="btn btn-outline-primary" for="instructor-radio-btn"
              ><i class="fas fa-fish fa-lg"></i><br />
              Fishing instructor</label
            >
          </div>
        </div>
        <div class="modal-body" v-if="mode === 'advertiserInfo'">
          <h5>Please inform us about reasons for joining our site</h5>
          <div class="form-floating">
            <textarea
              class="form-control"
              placeholder="Leave a comment here"
              id="floatingTextarea"
              v-model="registerReasons"
            ></textarea>
            <label for="floatingTextarea">Reasons for joining</label>
          </div>
        </div>
        <div class="modal-footer">
          <button
            type="button"
            v-if="mode == 'logIn'"
            class="btn btn-outline-primary"
          >
            Log in
          </button>
          <button
            type="button"
            v-if="
              mode == 'registerCustomer' ||
              mode == 'registerAdvertiser' ||
              mode == 'advertiserInfo'
            "
            class="btn btn-outline-primary"
            v-on:click="backClick"
          >
            <i class="fas fa-chevron-left fa-xs"></i> Back
          </button>
          <button
            v-on:click="registerUser"
            type="button"
            v-if="mode == 'registerCustomer' || mode == 'registerAdvertiser'"
            class="btn btn-outline-primary"
          >
            Create account
          </button>
          <button
            type="button"
            v-if="mode == 'registerRole' || mode == 'advertiserInfo'"
            class="btn btn-outline-primary"
            v-on:click="nextClick"
          >
            Next <i class="fas fa-chevron-right fa-xs"></i>
          </button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios";
export default {
  name: "RegisterModal",
  data: function () {
    return {
      mode: "logIn",
      chosenRole: "",
      registerReasons: "",
      user: {
        email: "",
        name: "",
        surname: "",
        street: "",
        city: "",
        country: "",
        phoneNumber: "",
        password1: "",
        password2: "",
      },
    };
  },
  mounted: function () {
    var element = document.getElementById("logIn-btn");
    element.classList.add("active");
  },
  methods: {
    registerClick: function () {
      this.mode = "registerRole";
      var element = document.getElementById("register-btn");
      element.classList.add("active");
      element = document.getElementById("logIn-btn");
      element.classList.remove("active");
    },
    logInClick: function () {
      this.mode = "logIn";
      var element = document.getElementById("logIn-btn");
      element.classList.add("active");
      element = document.getElementById("register-btn");
      element.classList.remove("active");
    },
    nextClick: function () {
      let elements = document.getElementsByName("btnradio");
      for (let e of elements) {
        if (e.checked) {
          this.chosenRole = e.value;
        }
      }

      if (this.registerReasons != "" && this.mode == "advertiserInfo") {
        this.mode = "registerAdvertiser";
      }

      if (this.chosenRole == "customer") {
        this.mode = "registerCustomer";
      } else if (this.mode == "registerRole" && this.chosenRole != "") {
        this.mode = "advertiserInfo";
      }
    },
    backClick: function () {
      if (this.mode == "registerCustomer" || this.mode === "advertiserInfo") {
        this.mode = "registerRole";
      } else if (this.mode == "registerAdvertiser") {
        this.mode = "advertiserInfo";
      }
    },
    closeModal: function () {
      this.mode = "logIn";
      this.chosenRole = "";
      this.registerReasons = "";
      this.user.email = "";
      this.user.name = "";
      this.user.surname = "";
      this.user.street = "";
      this.user.city = "";
      this.user.country = "";
      this.user.phoneNumber = "";
      this.user.password1 = "";
      this.user.password2 = "";
      var element = document.getElementById("logIn-btn");
      element.classList.add("active");
      element = document.getElementById("register-btn");
      element.classList.remove("active");
    },
    registerUser: function () {
      let user = {
        email: this.user.email,
        password: this.user.password1,
        name: this.user.name,
        surname: this.user.surname,
        phoneNumber: this.user.phoneNumber,
        userType: {
            name: "CLIENT"
        },
        address: {
            street: this.user.street,
            city: this.user.city,
            country: this.user.country
        }
      };

      axios
        .post("http://localhost:8080/auth/signup", user, {
          headers: {
            'Access-Control-Allow-Origin': 'http://localhost:8080'
          }
      })
        .then();
    },
  },
};
</script>

<style scoped src="@/css/registerModal.css"></style>
