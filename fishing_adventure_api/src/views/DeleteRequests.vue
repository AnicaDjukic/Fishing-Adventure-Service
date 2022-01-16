<template>
  <div>
    <div class="title">
      <h1>Deletion requests</h1>
      <i
        class="far fa-user-times fa-3x"
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
            v-on:keyup="searchRequests"
            v-on:click="searchRequests"
          />
        </div>
        <div class="col-md-4">
          <div style="display: flex">
            <div
              class="rating-div form-control"
              style="min-width: 115px;"
              v-on:click="searchRequests"
              
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
                    <th>
                        Date of request
                    </th>
                    <th>Email </th>
                    <th>Reason </th>
                    <th></th>
                </tr>
            </thead>
            <tbody>
                <tr v-for="request in requests" :key="request.id" v-bind:request="request">
                    <td>
                        <i v-if="request.role == 'ROLE_CLIENT'" class="fas fa-user fa-lg" style="color:#003148" aria-hidden="true"></i>
                        <i v-else-if="request.role == 'ROLE_ADMIN'" class="fas fa-cog fa-lg" style="color:#003148" aria-hidden="true"></i>
                        <i v-else-if="request.role == 'ROLE_FISHING_INSTRUCTOR'" class="fas fa-fish fa-lg" style="color:#003148" aria-hidden="true"></i>
                        <i v-else-if="request.role == 'ROLE_VACATION_HOME_OWNER'" class="fas fa-home fa-lg" style="color:#003148" aria-hidden="true"></i>
                        <i v-else-if="request.role == 'ROLE_BOAT_OWNER'" class="fas fa-anchor fa-lg" style="color:#003148" aria-hidden="true"></i>
                        </td>
                    <td>{{request.dateCreated}}</td>
                    <td>{{request.email}}</td>
                    <td>{{request.requestContent}} </td>
                    <td>
                        <button class="green-btn"
                        data-bs-toggle="modal"
                        :data-bs-target="'#deletion-request' + request.id"
                        > Respond</button>
                    </td>

                    <deletion-request
                    :id="'deletion-request' + request.id"
                    :request="request"
                    ></deletion-request>
                </tr>
                <!-- <tr v-if="searchResults.length == 0">
                    <td colspan="6"><h3  style="text-align:center" >No users found</h3></td>
                </tr> -->
            </tbody>
        </table>
      
    </div>
  </div>
 
</template>

<script>
import ReasonForDeletionModal from "@/components/Admin/ReasonForDeletionModal.vue";
import axios from "axios";
export default {
  components: { "deletion-request": ReasonForDeletionModal },
  data: function () {
    return {
      searchText: "",
      searchResults: [],
      requests: []
    };
  },
  mounted: function () {
    axios
      .get("http://localhost:8080/deleteRequest/getDeleteRequests", {
        headers: {
          "Access-Control-Allow-Origin": "http://localhost:8080",
          Authorization: "Bearer " + localStorage.refreshToken,
        },
      })
      .then((res) => {
        this.requests = res.data;
      });
  },
  methods: {
    searchRequests: function () {

    },
  },
};
</script>

<style scoped src="@/css/mycottages.css"></style>
<style scoped src="@/css/admin.css"></style>