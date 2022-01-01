<template>
  <div>
    <h6>(Optional)</h6>
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
          <tr v-for="priceItem in priceList" :key="priceItem.name">
            <td>
              <input
                placeholder="Description"
                type="text"
                class="login-inputs"
                style="border: 0; margin: 0"
                v-model="priceItem.name"
                v-on:change="emit"
              />
            </td>
            <td>
              <input
                placeholder="Price"
                type="number"
                class="login-inputs"
                style="border: 0; margin: 0"
                v-model="priceItem.price"
                v-on:change="emit"
              />
            </td>
            <td>
              <i
                class="far fa-trash-alt"
                style="color: #832626; cursor: pointer"
                v-on:click="removePriceItem(priceItem.name)"
              ></i>
            </td>
          </tr>
        </tbody>
      </table>
    </div>
  </div>
</template>

<script>
export default {
  props: ["priceList"],
  data: function () {
    return {
      newPriceList: [],
    };
  },
  mounted() {
    if (this.priceList) {
      this.newPriceList = this.priceList;
    }
  },
  methods: {
    emit: function () {
      let result = true;
      for (let item of this.newPriceList) {
        if (!item.desccription || !item.price) {
          result = false;
        }
      }
      console.log(result);

      let retVal = {
        data: this.newPriceList,
        result: result,
      };
      this.$emit("pricelistupdated", retVal);
    },
    addPriceListItem: function () {
      let newItem = {
        desccription: "",
        price: "",
      };
      this.newPriceList.push(newItem);
      this.emit();
    },
    removePriceItem: function (name) {
      for (var priceItem of this.priceList) {
        if (priceItem.name === name) {
          this.newPriceList.pop(priceItem);
        }
      }
      this.emit();
    },
  },
};
</script>

<style scoped>
.login-inputs {
  display: block;
  margin: 15px auto 0 auto;
  background-color: transparent;
  width: 100%;
  border-width: 0;
  border-bottom-width: 1px;
  border-color: white;
  padding: 2px 20px;
  color: white;
  font-size: 17px;
}

.table {
  color: white;
  margin-top: 2%;
}

.input-header {
  color: white;
  display: flex;
  justify-content: space-around;
  background-color: rgb(33, 37, 41);
  align-items: center;
}

.modal-body h6 {
  margin: 0;
  text-align: center;
}

.modal-body p {
  text-align: center;
}
</style>
