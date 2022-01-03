<template>
  <div>
    <h2 v-if="role == 'ROLE_CLIENT'" style="color: white">
      You have 2 penalties, after 3 penalties you will not be allow create any
      reservation
    </h2>
    <div class="card mb-3 bg-dark mt-3" style="width: 80%; margin: auto">
      <div class="row g-0">
        <div class="col-md-4 shadow-none">
          <img
            v-if="role != 'ROLE_CLIENT' && role != 'ROLE_ADMIN'"
            width="100"
            height="225"
            style="
              width: 100%;
              object-fit: cover;
              position: relative;
              padding-top: 10px;
            "
            src="@/assets/advertiser.png"
            class="img-fluid rounded-start shadow-none overlay"
          />
          <img
            v-if="role == 'ROLE_CLIENT'"
            width="100"
            height="225"
            style="
              width: 100%;
              object-fit: cover;
              position: relative;
              padding-top: 10px;
            "
            src="@/assets/client-male.png"
            class="img-fluid rounded-start shadow-none overlay"
          />
          <img
            v-if="role == 'ROLE_ADMIN'"
            width="100"
            height="225"
            style="
              width: 100%;
              object-fit: cover;
              position: relative;
              padding-top: 10px;
            "
            src="@/assets/admin.png"
            class="img-fluid rounded-start shadow-none overlay"
          />
        </div>
        <div class="col-md-8 shadow-none" name="main-col">
          <div class="card-body shadow-none p-0">
            <div
              class="btn-group"
              role="group"
              aria-label="Basic radio toggle button group"
            >
              <input
                type="radio"
                class="btn-check"
                name="btnradio"
                id="btnradio1"
                value="1"
                v-model="readioChecked"
                autocomplete="off"
              />
              <label class="btn navigation-btn" for="btnradio1">About me</label>

              <input
                type="radio"
                class="btn-check"
                name="btnradio"
                id="btnradio2"
                value="2"
                v-model="readioChecked"
                autocomplete="off"
              />
              <label class="btn navigation-btn" for="btnradio2">Account</label>
            </div>
            <div class="container" style="min-height: 320px">
              <div
                v-if="readioChecked == '1'"
                class="card-text shadow-none info-container"
                style=""
              >
                <div
                  class="shadow-none"
                  style="width: 100%; padding: 20px; text-align: left"
                >
                  <div class="row shadow-none">
                    <p class="card-text text-left shadow-none col-md-3">
                      Name:
                    </p>
                    <input
                      disabled
                      type="text"
                      v-model="name"
                      name="aboutMe"
                      class="shadow-none login-inputs col-md-8 mb-2"
                    />
                  </div>
                  <div class="row shadow-none">
                    <p class="card-text text-left shadow-none col-md-3">
                      Biography:
                    </p>
                    <textarea
                      name="aboutMe"
                      v-model="biography"
                      style="
                        min-height: 100px;
                        margin-bottom: 20px;
                        resize: none;
                      "
                      disabled
                      placeholder="Something about me.."
                      class="shadow-none login-inputs col-md-8"
                    />
                  </div>
                  <div class="row shadow-none">
                    <p class="card-text text-left shadow-none col-md-3">
                      Phonenumber:
                    </p>
                    <input
                      name="aboutMe"
                      type="text"
                      v-model="phonenumber"
                      disabled
                      class="shadow-none login-inputs col-md-8 mb-2"
                    />
                  </div>
                  <div class="row shadow-none">
                    <p class="card-text text-left shadow-none col-md-3">
                      Address:
                    </p>
                    <input
                      name="aboutMe"
                      type="text"
                      v-model="address"
                      disabled
                      class="shadow-none login-inputs col-md-8 mb-2"
                    />
                  </div>
                  <div class="row shadow-none">
                    <button
                      id="editAboutMe"
                      class="btn btn-primary col-md-4"
                      v-on:click="enableAboutMe"
                    >
                      Edit settings
                    </button>
                    <button
                      id="saveAboutMe"
                      style="display: none"
                      class="btn btn-primary col-md-4"
                      v-on:click="saveInfo"
                    >
                      Save
                    </button>
                  </div>
                  <h2 v-if="role == 'ROLE_CLIENT'" style="color: white">
                    User category: <b style="color: orange"> GOLD USER </b>
                  </h2>
                  <h2 v-if="role == 'ROLE_CLIENT'" style="color: white">
                    Your points: 50
                  </h2>
                </div>
              </div>
              <div
                v-if="readioChecked == '2'"
                class="card-text shadow-none info-container"
                style=""
              >
                <div class="shadow-none" style="width: 100%; padding: 40px">
                  <div class="row shadow-none">
                    <p class="card-text text-left shadow-none col-md-3">
                      Account:
                    </p>
                    <input
                      type="text"
                      v-model="account"
                      disabled
                      class="shadow-none login-inputs col-md-8 mb-2"
                    />
                  </div>
                  <div class="row shadow-none">
                    <p class="card-text text-left shadow-none col-md-3">
                      Password:
                    </p>
                    <input
                      name="account"
                      disabled
                      type="password"
                      class="login-inputs col-md-8 mb-2"
                      placeholder="Enter new password"
                      v-model="password1"
                    />
                  </div>
                  <div class="row shadow-none">
                    <p class="card-text text-left shadow-none col-md-3">
                      Password:
                    </p>
                    <input
                      name="account"
                      disabled
                      type="password"
                      class="login-inputs col-md-8 mb-2"
                      placeholder="Enter new password again"
                      v-model="password2"
                    />
                  </div>
                  <div class="row shadow-none mt-5">
                    <div class="col-md-20">
                      <button
                        class="btn btn-primary col-md-4"
                        id="editAccount"
                        v-on:click="enableAccount"
                      >
                        Change password
                      </button>
                      <button
                        id="saveAccount"
                        style="display: none"
                        class="btn btn-primary col-md-4"
                        v-on:click="changePassword"
                      >
                        Save
                      </button>
                      <span class="col-md-4 m-0 me-2"></span>
                      <button
                        id="deleteAccount"
                        class="btn btn-primary col-md-4"
                        style="
                          background-color: rgb(94, 23, 30);
                          border-color: rgb(94, 23, 30);
                        "
                      >
                        Delete account
                      </button>
                    </div>
                  </div>
                </div>
              </div>
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
  mounted: function () {
    if (localStorage.jwt) {
      axios
        .get("http://localhost:8080/users/getRole", {
          headers: {
            "Access-Control-Allow-Origin": "http://localhost:8080",
            Authorization: "Bearer " + localStorage.refreshToken,
          },
        })
        .then((res) => {
          this.role = res.data;
        });
    }

    let element = document.getElementById("btnradio1");
    element.checked = true;
    this.readioChecked = "1";
    axios
      .get("http://localhost:8080/users/get", {
        headers: {
          "Access-Control-Allow-Origin": "http://localhost:8080",
          Authorization: "Bearer " + localStorage.refreshToken,
        },
      })
      .then((res) => {
        this.name = res.data.name + " " + res.data.surname;
        this.account = res.data.email;
        this.phonenumber = res.data.phoneNumber;
        this.address =
          res.data.street + ", " + res.data.city + ", " + res.data.country;
        this.biography = res.data.biography;
      });
  },
  data: function () {
    return {
      role: undefined,
      password1: "",
      password2: "",
      readioChecked: "",
      name: "Derek Hayward",
      account: "darek.hayward@gmail.com",
      phonenumber: "347-393-6833",
      address: "3293 Cantebury Drive, Garden City",
      biography: "",
    };
  },
  methods: {
    enableAboutMe: function () {
      var elements = document.getElementsByName("aboutMe");
      var editBtn = document.getElementById("editAboutMe");
      var saveBtn = document.getElementById("saveAboutMe");
      saveBtn.style.display = "unset";
      editBtn.style.display = "none";
      for (let element of elements) {
        element.disabled = false;
      }
    },
    saveAboutMe: function () {
      var elements = document.getElementsByName("aboutMe");
      var editBtn = document.getElementById("editAboutMe");
      editBtn.style.display = "unset";
      var saveBtn = document.getElementById("saveAboutMe");
      saveBtn.style.display = "none";
      for (let element of elements) {
        element.disabled = true;
      }
    },
    changePassword: function () {
      let dto = {
        newPassword: this.password1,
        passwordAgain: this.password2,
      };

      if (this.password1 === this.password2) {
        axios.put("http://localhost:8080/users/changePassword", dto, {
          headers: {
            "Access-Control-Allow-Origin": "http://localhost:8080",
            Authorization: "Bearer " + localStorage.refreshToken,
          },
        });
      }
      this.saveAccount();
    },
    enableAccount: function () {
      var elements = document.getElementsByName("account");
      var editBtn = document.getElementById("editAccount");
      var saveBtn = document.getElementById("saveAccount");
      var deleteBtn = document.getElementById("deleteAccount");
      saveBtn.style.display = "unset";
      editBtn.style.display = "none";
      deleteBtn.style.display = "none";
      for (let element of elements) {
        element.disabled = false;
      }
    },
    saveAccount: function () {
      var elements = document.getElementsByName("account");
      var editBtn = document.getElementById("editAccount");
      var saveBtn = document.getElementById("saveAccount");
      var deleteBtn = document.getElementById("deleteAccount");
      editBtn.style.display = "unset";
      saveBtn.style.display = "none";
      deleteBtn.style.display = "unset";
      for (let element of elements) {
        element.disabled = true;
      }
      this.password1 = "";
      this.password2 = "";
    },
    saveInfo: function () {
      let user = {
        passwod: this.password1,
        name: this.name.split(" ")[0],
        surname: this.name.split(" ")[1],
        email: this.account,
        phoneNumber: this.phonenumber,
        street: this.address.split(", ")[0],
        city: this.address.split(", ")[1],
        country: this.address.split(", ")[2],
        biography: this.biography,
      };
      if (this.role != undefined) {
        axios
          .put("http://localhost:8080/users/update", user, {
            headers: {
              "Access-Control-Allow-Origin": "http://localhost:8080",
            },
          })
          .then((res) => {
            this.name = res.data.name + " " + res.data.surname;
            this.account = res.data.email;
            this.phonenumber = res.data.phoneNumber;
            this.address =
              res.data.street + ", " + res.data.city + ", " + res.data.country;
          });
      }

      this.saveAboutMe();
    },
  },
};
</script>

<style scoped>
.img-fluid {
  width: 100%;
  height: 100%;
}
.overlay {
  background: rgb(17, 16, 16);
  background: linear-gradient(
    90deg,
    rgba(17, 16, 16, 1) 0%,
    rgba(30, 34, 36, 1) 50%,
    rgba(33, 37, 41, 1) 100%
  );
}

.navigation-btn {
  border: 0;
  background-color: transparent;
  color: white;
  border-bottom: 0.5px solid white;
  font-size: 18px;
}

.navigation-btn:hover {
  transition: 0.2s;
  font-size: 19px;
}

.btn-check:checked + .navigation-btn {
  transition: 0.2s;
  font-weight: bold;
  font-size: 18.5px;
}
.login-inputs {
  height: 20%;
  display: block;
  background-color: transparent;
  border-width: 0;
  border-bottom-width: 0.5px;
  border-color: white;
  color: white;
}

.login-inputs:disabled {
  border-color: gray;
  color: gray;
}

.login-inputs-select {
  display: block;
  margin: 15px auto 0 auto;
  background-color: transparent;
  width: 58%;
  border-width: 0;
  border-bottom-width: 1px;
  border-color: white;
  padding: 2px 16px;
  color: white;
  font-size: 17px;
}

.login-inputs-select option {
  background-color: rgba(44, 53, 63, 1);
  color: white;
}

.login-inputs-select:focus {
  border-width: 1px;
}

#text1 {
  color: white;
  letter-spacing: 8px;
  font-size: 20px;
  animation: animation2 0.75s ease-out 0s forwards;
}

#text2 {
  margin-top: 20px;
  width: 50vh;
  animation: animation2 0.75s ease-out 1s forwards;
}
@keyframes animation1 {
  100% {
    opacity: 1;
  }
}

@keyframes animation2 {
  100% {
    opacity: 1;
    left: 0px;
  }
}

.info-container {
  display: flex;
  align-items: center;
  position: relative;
  left: 50px;
  opacity: 0;
  animation: animation2 0.2s ease-out 0s forwards;
}

.row {
  justify-content: center;
}

.btn-primary {
  background-color: #131517f3;
  border-color: #131517f3;
}
</style>
