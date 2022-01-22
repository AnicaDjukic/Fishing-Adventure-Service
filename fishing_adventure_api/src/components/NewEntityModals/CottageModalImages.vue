<template>
  <div>
    <h6
      style="
        color: white;
        display: block;
        margin: 15px 0 0 0;
        font-weight: bold;
      "
    >
      Images:
    </h6>
    <input
      type="file"
      class="login-inputs"
      style="margin: 2px auto 2px"
      id="inpFile"
      multiple
      v-on:changeview="fileUploaded"
      v-on:change="fileUploaded"
    />
    <label for="inpFile" id="inputLabel">
      <i class="fas fa-upload"></i> &nbsp; Choose Photos
    </label>

    <p id="num-of-files">No Files Chosen</p>
    <div id="images"></div>
  </div>
</template>

<script>
export default {
  props: ["files", "images"],
  data: function () {
    return {
      coverImage: undefined,
      env: undefined,
    };
  },
  mounted: function () {
    this.env = process.env.VUE_APP_URL;
    let fileInput = document.getElementById("inpFile");
    if (this.files) {
      fileInput.files = this.files;
      this.fileUploaded();
    } else if (this.images) {
      this.loadImages();
    }

    document.addEventListener("click", function (e) {
      if (e.target.classList.length > 0) {
        if (e.target.classList[0].includes("radio-image")) {
          console.log(e);
          document.coverImage = e.target.id;
        }
      }
    });
  },
  methods: {
    fileUploaded: function () {
      let fileInput = document.getElementById("inpFile");
      let imageContainer = document.getElementById("images");
      let numOfFiles = document.getElementById("num-of-files");

      this.$emit("uploaded", fileInput.files);
      imageContainer.innerHTML = "";
      numOfFiles.textContent = `${fileInput.files.length} Files Selected`;

      let counter = 0;
      for (let i of fileInput.files) {
        let div = document.createElement("div");
        let button = document.createElement("input");
        button.setAttribute("name", "frontImage");
        button.setAttribute("type", "radio");
        button.id = counter;
        button.hover;
        button.classList.add("radio-image");
        imageContainer.appendChild(button);
        let label = document.createElement("label");
        label.htmlFor = counter;
        let reader = new FileReader();
        let figCap = document.createElement("figcaption");
        figCap.innerHTML = i.name;
        figCap.style = "color:white;text-align:center";
        label.appendChild(figCap);
        reader.onload = () => {
          let img = document.createElement("img");
          img.className = "imageLoaded";
          img.style = "width:8rem";
          img.setAttribute("src", reader.result);
          label.insertBefore(img, figCap);
        };
        div.appendChild(button);
        div.appendChild(label);
        imageContainer.appendChild(div);

        reader.readAsDataURL(i);
        counter++;
      }
    },
    loadImages: function () {
      let imageContainer = document.getElementById("images");
      let numOfFiles = document.getElementById("num-of-files");

      imageContainer.innerHTML = "";
      numOfFiles.textContent = this.images.length + ` Files Selected`;

      let counter = 0;
      for (let i of this.images) {
        let div = document.createElement("div");
        let button = document.createElement("input");
        button.setAttribute("name", "frontImage");
        button.setAttribute("type", "radio");
        button.id = counter;
        button.hover;
        button.classList.add("radio-image");
        imageContainer.appendChild(button);
        let label = document.createElement("label");
        label.htmlFor = counter;
        let figCap = document.createElement("figcaption");
        figCap.innerHTML = i.path;
        figCap.style = "color:white;text-align:center";
        label.appendChild(figCap);
        let img = document.createElement("img");
        img.className = "imageLoaded";
        img.style = "width:8rem";
        img.setAttribute("src", this.env + "/downloadFile/" + i.path);
        label.insertBefore(img, figCap);
        div.appendChild(button);
        div.appendChild(label);
        imageContainer.appendChild(div);

        counter++;
      }
    },
  },
};
</script>

<style scoped src="@/css/imagesInModal.css"></style>
