<template>
  <div>
    <div class="title">
      <h1>All users</h1>
      <i
        class="far fa-users fa-3x"
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
        <div class="col-md-2">
          <button
            type="button"
            class="btn btn-outline-primary text-nowrap me-2"
            data-bs-toggle="modal"
            v-if="isHeadAdmin == true"
          >
            Add new admin
          </button>
        </div>
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
              style="min-width: 115px;"
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
                style="color: white; margin: -2% 0;"
              >
                User type
              </a>
              <ul
                class="dropdown-menu text-center dropdown-menu-dark"
                aria-labelledby="navbarScrollingDropdown"
              >
                <span class="dropdown-item" style="text-align:left"> 
                    <input type="checkbox" style="margin-right:10px; margin-left:5px">
                    <label style="font-size: 17px;">Clients</label>
                </span>
               <span class="dropdown-item" style="text-align:left"> 
                    <input type="checkbox" style="margin-right:10px; margin-left:5px">
                    <label style="font-size: 17px">Cottage owners</label>
                </span>
                <span class="dropdown-item" style="text-align:left"> 
                    <input type="checkbox" style="margin-right:10px; margin-left:5px">
                    <label style="font-size: 17px">Boat owners</label>
                </span>
                <span class="dropdown-item" style="text-align:left"> 
                    <input type="checkbox" style="margin-right:10px; margin-left:5px">
                    <label style="font-size: 17px">Fishing instructors</label>
                </span>
                <span class="dropdown-item"  style="text-align:left"> 
                    <input type="checkbox" style="margin-right:10px; margin-left:5px">
                    <label style="font-size: 17px">Administrators</label>
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
                    <th>Role</th>
                    <th v-on:click="sortByName" id="name-th">Name <i class="fa fa-sort"></i></th>
                    <th v-on:click="sortBySurname" id="surname-th">Surname <i class="fa fa-sort"></i></th>
                    <th v-on:click="sortByEmail" id="email-th">Email <i class="fa fa-sort"></i></th>
                    <th></th>
                </tr>
            </thead>
            <tbody>
                <tr v-for="user in users" :key="user.id" v-bind:user="user">
                    <td>
                        <i v-if="user.role == 'ROLE_CLIENT'" class="fas fa-user fa-lg" style="color:#003148" aria-hidden="true"></i>
                        <i v-else-if="user.role == 'ROLE_ADMIN'" class="fas fa-cog fa-lg" style="color:#003148" aria-hidden="true"></i>
                        <i v-else-if="user.role == 'ROLE_FISHING_INSTRUCTOR'" class="fas fa-fish fa-lg" style="color:#003148" aria-hidden="true"></i>
                        <i v-else-if="user.role == 'ROLE_VACATION_HOME_OWNER'" class="fas fa-home fa-lg" style="color:#003148" aria-hidden="true"></i>
                        <i v-else-if="user.role == 'ROLE_BOAT_OWNER'" class="fas fa-anchor fa-lg" style="color:#003148" aria-hidden="true"></i>
                        </td>
                    <td>{{user.name}}</td>
                    <td>{{user.surname}}</td>
                    <td>{{user.email}}</td>
                    <td>
                         <button class="black-btn" v-if="user.role =='ROLE_ADMIN' && isHeadAdmin == false"  disabled ><i class="fa fa-trash-o" aria-hidden="true"></i> Delete</button>
                        <button class="black-btn" v-else ><i class="fa fa-trash-o" aria-hidden="true"></i> Delete</button>
                    </td>
                </tr>
                <!-- <tr v-if="searchResults.length == 0">
                    <td colspan="6"><h3  style="text-align:center" >No users found</h3></td>
                </tr> -->
            </tbody>
        </table>
        <div class="loyalty-button">
          <button type="button" class="btn btn-secondary" style="background-color: #DAA520; color: black; font-size: 105%"
            data-bs-toggle="modal"
            data-bs-target="#loyalty-program-modal">
           <i class="fas fa-crown"></i> Loyalty program
          </button>
        </div>
    </div>
    <loyalty-program-modal
    id="loyalty-program-modal">
    </loyalty-program-modal>

  </div>
 
</template>

<script>
import LoyaltyProgramModal from "@/components/Admin/LoyaltyProgramModal.vue"
import axios from "axios";
axios.defaults.baseURL = process.env.VUE_APP_URL;
export default {
  components: { "loyalty-program-modal": LoyaltyProgramModal },
  data: function () {
    return {
      searchText: "",
      searchResults: [],
      users: [],
      isHeadAdmin: false,
    };
  },
  mounted: function () {
    axios
      .get("/users/getAllUsers", {
        headers: {
          "Access-Control-Allow-Origin": process.env.VUE_APP_URL,
          Authorization: "Bearer " + localStorage.refreshToken,
        },
      })
      .then((res) => {
        this.users = res.data;
      });

    axios
      .get("admin/isHeadAdmin", {
        headers: {
          "Access-Control-Allow-Origin": process.env.VUE_APP_URL,
          Authorization: "Bearer " + localStorage.refreshToken,
        },
      })
      .then((res) => {
        this.isHeadAdmin = res.data;
      });
  },
  methods: {
    searchUsers: function () {

    },
  },
};
</script>

<style scoped src="@/css/mycottages.css"></style>
<style scoped src="@/css/admin.css"></style>