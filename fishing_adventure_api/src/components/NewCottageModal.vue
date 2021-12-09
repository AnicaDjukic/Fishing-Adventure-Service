<template>
  <div
    class="modal fade dark"
    id="NewCottageModal"
    tabindex="-1"
    aria-labelledby="NewCottageModalLabel"
    aria-hidden="true"
  >
    <div class="modal-dialog modal-dialog-centered">
      <div class="modal-content">
        <div class="modal-header">
          <h3
            v-if="mode == '1'"
            style="
              color: white;
              font-weight: bold;
              font-size: 20px;
              margin-left: auto;
            "
          >
            NEW COTTAGE
          </h3>
          <h3
            v-if="
              (mode == '2') | (mode === '3') | (mode === '4') | (mode === '5')
            "
            style="
              color: white;
              font-weight: bold;
              font-size: 20px;
              margin-left: auto;
            "
          >
            {{ cottageName }}
          </h3>
          <button
            type="button"
            class="btn-close"
            data-bs-dismiss="modal"
            v-on:click="closeModal"
          ></button>
        </div>
        <div class="modal-body" v-if="mode == '1'">
          <h6 style="color: white">Please enter information:</h6>
          <form>
            <input
              v-model="cottageName"
              type="text"
              class="login-inputs"
              placeholder="Cottage name"
            />

            <textarea
              class="login-inputs"
              placeholder="Cottage description"
              style="resize: none"
              v-model="cottageDescription"
            />

            <label
              style="
                color: white;
                display: block;
                margin: 15px 0 0 0;
                font-weight: bold;
              "
              >Images:</label
            >
            <input
              type="file"
              class="login-inputs"
              style="margin: 2px auto 2px"
              id="inpFile"
              multiple
              v-on:change="fileUploaded"
            />

            <div class="image-preview" id="imagePreview">
              <img
                style="max-width: 60%; max-height: 110px"
                src=""
                alt="Image Preview"
                class="image-preview__image"
              />
              <span class="image-preview__default-text">Image Preview</span>
            </div>
          </form>
          <label
            class="error"
            id="cottageImagesErr"
            name="labels"
            display="hidden"
          >
          </label>
        </div>
        <div class="modal-body" v-if="mode === '2'">
          <div class="login-title">
            <p id="secondErr">
              It is necessary to determine the location of the cottage by
              filling in
              <b><em>all</em></b> the fields listed. Fields can also be filled
              by clicking on the desired location on the map.
            </p>
          </div>
          <form>
            <input
              v-model="street"
              id="street"
              type="text"
              class="login-inputs"
              placeholder="Street"
            />
            <input
              v-model="houseNumber"
              id="number"
              type="text"
              class="login-inputs"
              placeholder="Number"
            />
            <input
              v-model="city"
              id="city"
              type="text"
              class="login-inputs"
              placeholder="City"
            />
            <input
              v-model="country"
              id="country"
              type="text"
              class="login-inputs"
              placeholder="Country"
            />

            <div id="map"></div>
          </form>
        </div>
        <div class="modal-body" v-if="mode === '3'">
          <h6>(Additional)</h6>
          <h6>Plase fill extra information about your cottage:</h6>

          <div style="margin-top: 5%">
            <div class="input-header">
              <h6>Rooms</h6>
              <i class="far fa-plus-square" v-on:click="addRoom"></i>
            </div>
            <table class="table">
              <thead>
                <th>Room</th>
                <th>Number of beds</th>
                <th>Delete</th>
              </thead>
              <tbody>
                <tr v-for="room in rooms" :key="room.id">
                  <td>
                    <input
                      placeholder="Name"
                      type="text"
                      class="login-inputs"
                      style="border: 0; margin: 0"
                      v-model="room.name"
                    />
                  </td>
                  <td>
                    <input
                      placeholder="Beds"
                      type="text"
                      class="login-inputs"
                      style="border: 0; margin: 0"
                      v-model="room.beds"
                    />
                  </td>
                  <td>
                    <i
                      class="far fa-trash-alt"
                      v-on:click="removeRoom(room.id)"
                    ></i>
                  </td>
                </tr>
              </tbody>
            </table>
          </div>
        </div>
        <div class="modal-body" v-if="mode === '4'">
          <h6>(Additional)</h6>
          <h6>Plase fill extra information about your cottage:</h6>

          <div style="margin-top: 5%">
            <div class="input-header">
              <h6>Rule book</h6>
              <i class="far fa-check-circle" v-on:click="addPositiveRule"></i>
              <i class="far fa-times-circle" v-on:click="addNegativeRule"></i>
            </div>
            <table class="table">
              <thead>
                <th>Type</th>
                <th>Rule</th>
                <th>Delete</th>
              </thead>
              <tbody>
                <tr v-for="rule in rules" :key="rule.id">
                  <td>
                    <i
                      class="far fa-check-circle"
                      v-if="rule.type == 'positive'"
                    ></i>
                    <i
                      class="far fa-times-circle"
                      v-if="rule.type == 'negative'"
                    ></i>
                  </td>
                  <td>
                    <input
                      placeholder="Description"
                      type="text"
                      class="login-inputs"
                      style="border: 0; margin: 0"
                      v-model="rule.description"
                    />
                  </td>
                  <td>
                    <i
                      class="far fa-trash-alt"
                      v-on:click="removeRule(rule.id)"
                    ></i>
                  </td>
                </tr>
              </tbody>
            </table>
          </div>
        </div>
        <div class="modal-body" v-if="mode === '5'">
          <h6>(Additional)</h6>
          <h6>Plase fill extra information about your cottage:</h6>

          <div style="margin-top: 5%">
            <div class="input-header">
              <h6>Price list</h6>
              <i class="far fa-plus-square" v-on:click="addPriceListItem"></i>
            </div>
            <table class="table">
              <thead>
                <th>Additional service</th>
                <th>
                  Price
                  <i class="fas fa-dollar-sign" style="margin-right: 20px"></i>
                </th>
                <th>Delete</th>
              </thead>
              <tbody>
                <tr v-for="priceItem in priceList" :key="priceItem.id">
                  <td>
                    <input
                      placeholder="Description"
                      type="text"
                      class="login-inputs"
                      style="border: 0; margin: 0"
                      v-model="priceItem.description"
                    />
                  </td>
                  <td>
                    <input
                      placeholder="Price"
                      type="number"
                      class="login-inputs"
                      style="border: 0; margin: 0"
                      v-model="priceItem.price"
                    />
                  </td>
                  <td>
                    <i
                      class="far fa-trash-alt"
                      v-on:click="removePriceItem(priceItem.id)"
                    ></i>
                  </td>
                </tr>
              </tbody>
            </table>
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
        <div class="modal-footer steps-div">
          <button
            v-on:click="backClick"
            type="button"
            id="back-btn"
            style="visibility: hidden"
            class="btn btn-outline-primary"
          >
            <i class="fas fa-chevron-left fa-xs"></i> Back
          </button>
          <div style="color: white; width: 100px" v-if="parseInt(mode) != 5">
            <i
              class="fa fa-square"
              aria-hidden="true"
              style="margin: 2%"
              v-for="index in parseInt(mode)"
              :key="index"
            ></i>
            <i
              class="fa fa-square-o"
              aria-hidden="true"
              style="margin: 2%"
              v-for="index in 5 - parseInt(mode)"
              :key="index"
            ></i>
          </div>
          <button
            type="button"
            v-if="parseInt(mode) < 5"
            class="btn btn-outline-primary"
            v-on:click="nextClick"
          >
            Next <i class="fas fa-chevron-right fa-xs"></i>
          </button>
          <button
            type="button"
            v-if="mode == '5'"
            class="btn btn-outline-primary"
            v-on:click="createCottage"
          >
            Create
          </button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  props: ["cottage"],
  name: "RegisterModal",
  data: function () {
    return {
      mode: "1",
      cottageName: "",
      cottageDescription: "",
      images: [],
      street: "",
      houseNumber: "",
      city: "",
      country: "",
      rooms: [],
      rules: [],
      priceList: [],
      roomsId: 0,
      rulesId: 0,
      priceListId: 0,
    };
  },
  mounted: function () {
    var element = document.getElementById("logIn-btn");
    element.classList.add("active");
    if (this.cottage) {
      this.cottageName = this.cottage.name;
      this.cottageDescription = this.cottage.description;
      this.images = this.cottage.image;
      this.street = this.cottage.location.street;
      this.houseNumber = this.cottage.location.houseNumber;
      this.city = this.cottage.location.city;
      this.country = this.cottage.location.country;
      this.rooms = this.cottage.rooms;
      this.rules = this.cottage.rules;
      this.priceList = this.cottage.additionalServices;
    }
  },
  methods: {
    nextClick: function () {
      if (this.mode == "1") {
        if (
          !this.cottageName ||
          !this.cottageDescription ||
          this.images.length == 0
        ) {
          document.getElementById("cottageImagesErr").innerHTML =
            "All fields must be filled.";
        } else {
          this.mode = "2";
          document.getElementById("back-btn").style.visibility = "visible";
          document.getElementById("cottageImagesErr").innerHTML = "";
        }
      } else if (this.mode == "2") {
        if (!this.street || !this.houseNumber || !this.city || !this.country) {
          document.getElementById("secondErr").style = "color: red";
        } else {
          this.mode = "3";
          document.getElementById("secondErr").style = "color: white";
        }
      } else if (this.mode == "3") {
        this.mode = "4";
      } else if (this.mode == "4") {
        this.mode = "5";
      }
    },
    backClick: function () {
      if (this.mode == "2") {
        this.mode = "1";
        document.getElementById("back-btn").style.visibility = "hidden";
      } else if (this.mode == "3") {
        this.mode = "2";
      } else if (this.mode == "4") {
        this.mode = "3";
      } else if (this.mode == "5") {
        this.mode = "4";
      }
    },
    closeModal: function () {
      this.mode = "1";
      this.cottageName = "";
      this.cottageDescription = "";
      this.images = [];
      (this.street = ""), (this.houseNumber = "");
      this.city = "";
      this.country = "";
      this.rooms = [];
      this.rules = [];
      this.priceList = [];
      this.roomsId = 0;
      this.rulesId = 0;
      this.priceListId = 0;
    },
    fileUploaded: function () {
      let inpFile = document.getElementById("inpFile");
      let imagePreviewContainer = document.getElementById("imagePreview");
      let previewImage = imagePreviewContainer.querySelector(
        ".image-preview__image"
      );
      let previewDefaultText = imagePreviewContainer.querySelector(
        ".image-preview__default-text"
      );

      let file = inpFile.files[0];

      if (file) {
        let reader = new FileReader();

        previewDefaultText.style.display = "none";
        previewImage.style.display = "block";

        let ref = this;
        reader.addEventListener("load", function () {
          previewImage.setAttribute("src", this.result);
          ref.images.push(this.result);
        });

        reader.readAsDataURL(file);
      } else {
        previewDefaultText.style.display = null;
        previewImage.style.display = null;
        previewImage.setAttribute("src", "");
      }
    },
    addRoom: function () {
      let newRoom = {
        id: this.roomsId,
        name: "",
        beds: "",
      };
      this.roomsId = this.roomsId + 1;
      this.rooms.push(newRoom);
    },
    addPositiveRule: function () {
      let newRule = {
        id: this.rulesId,
        desccription: "",
        type: "positive",
      };
      this.rulesId = this.rulesId + 1;
      this.rules.push(newRule);
    },
    addNegativeRule: function () {
      let newRule = {
        id: this.rulesId,
        desccription: "",
        type: "negative",
      };
      this.rulesId = this.rulesId + 1;
      this.rules.push(newRule);
    },
    addPriceListItem: function () {
      let newItem = {
        id: this.priceListId,
        desccription: "",
        price: "",
      };
      this.priceListId = this.priceListId + 1;
      this.priceList.push(newItem);
    },
    removeRoom: function (id) {
      for (var room of this.rooms) {
        if (room.id === id) {
          this.rooms.pop(room);
        }
      }
    },
    removeRule: function (id) {
      for (var rule of this.rules) {
        if (rule.id === id) {
          this.rules.pop(rule);
        }
      }
    },
    removePriceItem: function (id) {
      for (var priceItem of this.priceList) {
        if (priceItem.id === id) {
          this.priceList.pop(priceItem);
        }
      }
    },
    createCottage: function () {},
  },
};
</script>

<style scoped src="@/css/newCottageModal.css"></style>
