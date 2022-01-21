<template>
  <div
    class="modal fade dark"
    id="AddAdminModal"
    tabindex="-1"
    aria-labelledby="LogInModalLabel"
    aria-hidden="true"
  >
    <div class="modal-dialog modal-dialog-centered">
      <div class="modal-content">
        <div class="modal-header">
          <h3 style="font-size: 22px; margin: 0 0 0 auto; color: white">
            Add new administrator
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
          <h5>Please enter all of the information</h5>
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
       
        <div class="modal-footer">
          <p id="msg"></p>

          <button
            v-on:click="registerUser"
            type="button"
            id="create-btn"
            class="btn btn-outline-primary"
          >
            Create account
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
  name: "RegisterModal",
  data: function () {
    return {
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

  },
  methods: {
    closeModal: function () {
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
      document.getElementById("create-btn").style.visibility = "visible";
      document.getElementById("back-btn").style.visibility = "visible";
      document.getElementById("msg").innerHTML = "";
    },
    registerUser: function () {
      let user = {
        email: this.user.email,
        password: this.user.password1,
        name: this.user.name,
        surname: this.user.surname,
        phoneNumber: this.user.phoneNumber,
        userType: {
          name: "ROLE_ADMIN",
        },
        address: {
          street: this.user.street,
          city: this.user.city,
          country: this.user.country,
          zipCode: 0
        },
        biography: "",
      };

      axios
        .post("/auth/signup/admin", user, {
          headers: {
            "Access-Control-Allow-Origin": process.env.VUE_APP_URL,
            Authorization: "Bearer " + localStorage.refreshToken,
          },
        })
        .then(() => {
          var myModalEl = document.getElementById('AddAdminModal');
          var modal = window.bootstrap.Modal.getInstance(myModalEl)
          modal.hide();
        });
    },
  },
};
</script>

<style scoped src="@/css/registerModal.css"></style>
