<template>
  <div>
    <div class="title">
      <h1>Client complaints</h1>
      <i
        class="far fa-thumbs-down fa-4x"
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
        style="justify-content: space-evenly; align-items: center; height: 38px; color: #dddddd; font-size: 20px"
      >
        
        
      </div>
    </div>
    <div>
        <table class="table-users">
            <thead>
                <tr>
                    <th>Client </th>
                    <th>Service</th>
                    <th>Advertiser</th>
                    <th>Complaint </th>
                    <th></th>
                </tr>
            </thead>
            <tbody>
                <tr v-for="complaint in complaints" :key="complaint.id" v-bind:complaint="complaint">
                    <td>{{complaint.clientFullName}}</td>
                    <td>{{complaint.serviceName}}</td>
                    <td>{{complaint.advertiserFullName}}</td>
                    <td>{{complaint.content}} </td>
                    <td>
                        <button class="green-btn"
                        data-bs-toggle="modal"
                        :data-bs-target="'#complaint-response' + complaint.id"
                        > Respond</button>
                    </td>

                    <complaint-response
                    :id="'complaint-response' + complaint.id"
                    :complaint="complaint"
                    ></complaint-response>
                </tr>
            </tbody>
        </table>
      
    </div>
  </div>
 
</template>

<script>
import ComplaintResponseModal from "@/components/Admin/ComplaintResponseModal.vue";
import axios from "axios";
export default {
  components: { "complaint-response": ComplaintResponseModal },
  data: function () {   
    return {
      searchText: "",
      searchResults: [],
      complaints: []
    };
  },
  mounted: function () {
    axios
      .get("http://localhost:8080/complaint/getClientComplaints", {
        headers: {
          "Access-Control-Allow-Origin": "http://localhost:8080",
          Authorization: "Bearer " + localStorage.refreshToken,
        },
      })
      .then((res) => {
        this.complaints = res.data;
      });
  },
  methods: {
    sanctionClient: function (report) {
        axios
          .put("http://localhost:8080/reservationReport/sanctionClient/", report, {
            headers: {
              "Access-Control-Allow-Origin": "http://localhost:8080",
              Authorization: "Bearer " + localStorage.refreshToken,
            },
          })
          .then(() => {
            this.reports.splice(this.reports.indexOf(report));
            this.$toast.show(
            "User has been sanctioned.",
            {
              duration: 3000,
            }
          );
          });
    },
    dontSanctionClient: function (report) {
        axios
          .put("http://localhost:8080/reservationReport/rejectPenalty/", report, {
            headers: {
              "Access-Control-Allow-Origin": "http://localhost:8080",
              Authorization: "Bearer " + localStorage.refreshToken,
            },
          })
          .then(this.reports.splice(this.reports.indexOf(report)));
    },
  },
};
</script>

<style scoped src="@/css/mycottages.css"></style>
<style scoped src="@/css/admin.css"></style>