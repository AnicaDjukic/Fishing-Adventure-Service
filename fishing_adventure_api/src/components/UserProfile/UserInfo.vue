<template>
  <div class="fa-page">
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
                <ul class="navbar-nav me-auto mb-2 mb-lg-0 menu-ol">
                  <li
                    v-on:click="changeMenuDisplay"
                    id="about"
                    style="border-bottom: 1px solid white"
                  >
                    About me
                  </li>
                  <li v-on:click="changeMenuDisplay" id="account">
                    Account
                  </li>
                </ul>
              </div>
            </div>
          </nav>

          <div class="menu">
            <div class="menu-body">
              <div class="menu-about" style="text-align: justify">
                <div
                  class="card-text shadow-none info-container"
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
                      
                  </div>
                </div>
              </div>

              <div class="menu-account" style="display: none">
                <div class="card-text shadow-none info-container">
                  <div class="shadow-none" style="width: 100%; padding: 20px">
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
                    <div class="row shadow-none" v-if="isRequested">
                      <p class="card-text text-left shadow-none ">
                        <pre style="overflow: inherit;color: rgb(219 89 102)"><i
                          class="fas fa-exclamation-triangle"
                          style="color: rgb(219 89 102)"
                        ></i> Account requested for deletion.
                        </pre>
                      </p>
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
                          class="btn btn-primary col-md-4"
                          style="
                            background-color: rgb(94, 23, 30);
                            border-color: rgb(94, 23, 30);
                          "
                          data-bs-toggle="modal"
                          :data-bs-target="'#deleteAccount'"
                          v-if="!isRequested"
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
  </div>
  <DeleteAccountModal id="deleteAccount" v-on:deleterequestsent="deleteRequestSent"></DeleteAccountModal>
</template>

<script>
import axios from "axios";
axios.defaults.baseURL = process.env.VUE_APP_URL;
import DeleteAccountModal from "@/components/UserProfile/DeleteAccountModal.vue"
export default {
  data: function () {
    return {
      role: undefined,
      password1: "",
      password2: "",
      name: "Derek Hayward",
      account: "darek.hayward@gmail.com",
      phonenumber: "347-393-6833",
      address: "3293 Cantebury Drive, Garden City",
      biography: "",
      isRequested: false,
    };
  },
  components: { DeleteAccountModal },
  mounted: function () {
    axios
      .get("/deleteRequest/isRequested", {
        headers: {
          "Access-Control-Allow-Origin": process.env.VUE_APP_URL,
          Authorization: "Bearer " + localStorage.refreshToken,
        },
      })
      .then((res) => {
        this.isRequested = res.data;
      });
    if (localStorage.jwt) {
      axios
        .get("/users/getRole", {
          headers: {
            "Access-Control-Allow-Origin": process.env.VUE_APP_URL,
            Authorization: "Bearer " + localStorage.refreshToken,
          },
        })
        .then((res) => {
          this.role = res.data;
        });
    }

    axios
      .get("/users/get", {
        headers: {
          "Access-Control-Allow-Origin": process.env.VUE_APP_URL,
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
  methods: {
    deleteRequestSent: function(){
      this.isRequested = true;
    },
    changeMenuDisplay: function (event) {
      let elID = event.currentTarget.id;
      document.getElementById("about").style.borderBottom = "0px";
      document.getElementById("account").style.borderBottom = "0px";

      document.getElementById(elID).style.borderBottom = "1px solid white";

      if (elID == "about") {
        document.querySelector(".menu-about").style.display = "block";
        document.querySelector(".menu-account").style.display = "none";
      } else if (elID == "account") {
        document.querySelector(".menu-about").style.display = "none";
        document.querySelector(".menu-account").style.display = "block";
      } else {
        document.querySelector(".menu-about").style.display = "none";
        document.querySelector(".menu-account").style.display = "none";
      }
    },
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
        axios.put("/users/changePassword", dto, {
          headers: {
            "Access-Control-Allow-Origin": process.env.VUE_APP_URL,
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
          .put("/users/update", user, {
            headers: {
              "Access-Control-Allow-Origin": process.env.VUE_APP_URL,
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

<style scoped src="@/css/userInfo.css">

</style>
