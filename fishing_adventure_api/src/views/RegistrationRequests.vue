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
                    <th v-on:click="sortByDate" id="name-th">Date of request <i class="fa fa-sort"></i></th>
                    <th v-on:click="sortByName" id="name-th">Name <i class="fa fa-sort"></i></th>
                    <th v-on:click="sortBySurname" id="surname-th">Surname <i class="fa fa-sort"></i></th>
                    <th v-on:click="sortByEmail" id="email-th">Email <i class="fa fa-sort"></i></th>
                </tr>
            </thead>
            <tbody>
                <tr v-for="user in users" :key="user.id" v-bind:user="user" data-bs-toggle="modal"
                    :data-bs-target="'#registration-request' + user.id">
                 
                    <td>
                        <i v-if="user.userType == 'CLIENT'" class="fas fa-user fa-lg" style="color:#003148" aria-hidden="true"></i>
                        <i v-else-if="user.userType == 'ADMINISTRATOR'" class="fas fa-cog fa-lg" style="color:#003148" aria-hidden="true"></i>
                        <i v-else-if="user.userType == 'FISHING_INSTRUCTOR'" class="fas fa-fish fa-lg" style="color:#003148" aria-hidden="true"></i>
                        <i v-else-if="user.userType == 'COTTAGE_OWNER'" class="fas fa-home fa-lg" style="color:#003148" aria-hidden="true"></i>
                        <i v-else-if="user.userType == 'BOAT_OWNER'" class="fas fa-anchor fa-lg" style="color:#003148" aria-hidden="true"></i>
                    </td>
                    <td>
                        {{user.date}}
                    </td>
                    <td>{{user.name}}</td>
                    <td>{{user.surname}}</td>
                    <td>{{user.email}}</td>

                    <registration-request :id="'registration-request' + user.id" :user="user"></registration-request>
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
import RequestForRegistration from "@/components/RequestForRegistration.vue";

export default {
    components: { "registration-request": RequestForRegistration },
    data: function () {
        return {
        searchText: "",
        searchResults: [],
        selectedUser: undefined,
        users: [
            {
            id: "1",
            name: "Curtis",
            surname: "Lee",
            email: "curtis_lee@gmail.com",
            userType: "BOAT_OWNER",
            date: "03.12.2021.",
            telephone: "0634375921",
            country: "Serbia",
            city: "City",
            street: "Street 2",
            reason_for_registration: "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Phasellus condimentum ut lectus et efficitur. Quisque finibus facilisis rutrum. Mauris sed ipsum congue, euismod mauris ac, volutpat mi. Sed sollicitudin ullamcorper varius. Ut ullamcorper ac ex a suscipit." 
            },
            {
            id: "2",
            name: "Amy",
            surname: "Larsson",
            email: "simon_smith@gmail.com",
            userType: "COTTAGE_OWNER",
            date: "01.12.2021.",
            telephone: "0632868924",
            country: "Germany",
            city: "City",
            street: "Street 7",
            reason_for_registration: "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Phasellus condimentum ut lectus et efficitur." 
            },
            {
            id: "3",
            name: "Nick",
            surname: "Ness",
            email: "nick@gmail.com",
            userType: "FISHING_INSTRUCTOR",
            date: "29.11.2021.",
            telephone: "0634825938",
            country: "Norway",
            city: "City",
            street: "Street 8",
            reason_for_registration: "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Phasellus condimentum ut lectus et efficitur." 
            }
        ]
        };
    },
    methods: {
        searchUsers: function () {

        },
        showRequest: function(user) {
            this.selectedUser = user;
        }
    },
};
</script>

<style scoped src="@/css/mycottages.css"></style>
<style scoped src="@/css/admin.css"></style>


