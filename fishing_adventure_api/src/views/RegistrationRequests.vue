<template>
  <div>
    <div class="title">
      <h1>Registration requests</h1>
      <i
        class="fas fa-envelope-open-text fa-3x"
        style="font-family: 'Font Awesome 5 Pro'"
      ></i>
    </div>
    <div
      style="
        background-color: #212529;
        padding: 15px;
        text-align: -webkit-center;
      "
    >
      <div
        class="container w-100 row row-cols-1 row-cols-sm-1 row-cols-md-4"
        style="justify-content: space-evenly; align-items: center"
      >
        <div class="col-md-3">
          <input
            class="form-control me-2"
            type="search"
            placeholder="Search"
            aria-label="Search"
            v-model="searchText"
            v-on:keyup="searchUsers"
            v-on:click="searchUsers"
          />
        </div>
        <div class="col-md-4">
          <div style="display: flex">
            <div
              class="rating-div form-control"
              style="min-width: 115px"
              v-on:click="searchUsers"
            >
              <div class="nav-item dropdown">
                <a
                  class="nav-link dropdown-toggle"
                  href="#"
                  id="navbarScrollingDropdown"
                  role="button"
                  data-bs-toggle="dropdown"
                  aria-expanded="false"
                  style="color: white; margin: -2% 0"
                >
                  User type
                </a>
                <ul
                  class="dropdown-menu text-center dropdown-menu-dark"
                  aria-labelledby="navbarScrollingDropdown"
                >
                  <span class="dropdown-item" style="text-align: left">
                    <input
                      type="checkbox"
                      style="margin-right: 10px; margin-left: 5px"
                    />
                    <label style="font-size: 17px">Cottage owners</label>
                  </span>
                  <span class="dropdown-item" style="text-align: left">
                    <input
                      type="checkbox"
                      style="margin-right: 10px; margin-left: 5px"
                    />
                    <label style="font-size: 17px">Boat owners</label>
                  </span>
                  <span class="dropdown-item" style="text-align: left">
                    <input
                      type="checkbox"
                      style="margin-right: 10px; margin-left: 5px"
                    />
                    <label style="font-size: 17px">Fishing instructors</label>
                  </span>
                </ul>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
    <div>
      <table class="table-users">
        <thead>
          <tr>
            <th>Advertiser type</th>
            <!-- <th v-on:click="sortByDate" id="name-th">
              Date of request <i class="fa fa-sort"></i>
            </th> -->
            <th v-on:click="sortByName" id="name-th">
              Name <i class="fa fa-sort"></i>
            </th>
            <th v-on:click="sortBySurname" id="surname-th">
              Surname <i class="fa fa-sort"></i>
            </th>
            <th v-on:click="sortByEmail" id="email-th">
              Email <i class="fa fa-sort"></i>
            </th>
          </tr>
        </thead>
        <tbody>
          <tr
            v-for="user in users"
            :key="user.id"
            v-bind:user="user"
            data-bs-toggle="modal"
            :data-bs-target="'#registration-request' + user.id"
          >
            <td>
              <i
                v-if="user.userType == 'ROLE_CLIENT'"
                class="fas fa-user fa-lg"
                style="color: #003148"
                aria-hidden="true"
              ></i>
              <i
                v-else-if="user.userType == 'ROLE_ADMIN'"
                class="fas fa-cog fa-lg"
                style="color: #003148"
                aria-hidden="true"
              ></i>
              <i
                v-else-if="user.userType == 'ROLE_FISHING_INSTRUCTOR'"
                class="fas fa-fish fa-lg"
                style="color: #003148"
                aria-hidden="true"
              ></i>
              <i
                v-else-if="user.userType == 'ROLE_VACATION_HOME_OWNER'"
                class="fas fa-home fa-lg"
                style="color: #003148"
                aria-hidden="true"
              ></i>
              <i
                v-else-if="user.userType == 'ROLE_BOAT_OWNER'"
                class="fas fa-anchor fa-lg"
                style="color: #003148"
                aria-hidden="true"
              ></i>
            </td>
            <!-- <td>
              {{ user.date }}
            </td> -->
            <td>{{ user.name }}</td>
            <td>{{ user.surname }}</td>
            <td>{{ user.email }}</td>

            <registration-request
              :id="'registration-request' + user.id"
              :user="user"
              @rejectRequest="rejectRequest"
            ></registration-request>
          </tr>
          <!-- <tr v-if="searchResults.length == 0">
                    <td colspan="6"><h3  style="text-align:center" >No users found</h3></td>
                </tr> -->
        </tbody>
      </table>
    </div>
    <ReasonForRejectionModal
    id="ReasonForRejectionModal"
    :email="email"
  ></ReasonForRejectionModal>
  </div>
</template>

<script>
import RequestForRegistration from "@/components/Admin/RequestForRegistration.vue";
import ReasonForRejectionModal from "@/components/Admin/ReasonForRejectionModal.vue";
import axios from "axios";
axios.defaults.baseURL = process.env.VUE_APP_URL;

export default {
  components: { "registration-request": RequestForRegistration, ReasonForRejectionModal },
  data: function () {
    return {
      searchText: "",
      searchResults: [],
      selectedUser: undefined,
      users: [],
      email: "",
    };
  },
  mounted: function () {
    axios
      .get("/users/getAllRegistrationRequests", {
        headers: {
          "Access-Control-Allow-Origin": process.env.VUE_APP_URL,
          Authorization: "Bearer " + localStorage.refreshToken,
        },
      })
      .then((res) => {
        this.users = res.data;
        console.log(this.users)
      });
  },
  methods: {
    searchUsers: function () {},
    showRequest: function (user) {
      this.selectedUser = user;
    },
    rejectRequest(email) {
      this.email = email;
      var myModal = document.getElementById("ReasonForRejectionModal");
      var modal = window.bootstrap.Modal.getOrCreateInstance(myModal);
      modal.show();
    },
  },
};
</script>

<style scoped src="@/css/mycottages.css"></style>
<style scoped src="@/css/admin.css"></style>
