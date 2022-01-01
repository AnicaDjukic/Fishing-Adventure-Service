<template>
  <div>
    <h6>(Optional)</h6>
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
          <tr v-for="rule in rules" :key="rule.content">
            <td>
              <i
                class="far fa-check-circle"
                style="color: green"
                v-if="rule.isEnforced == true"
              ></i>
              <i
                class="far fa-times-circle"
                style="color: red"
                v-if="rule.isEnforced == false"
              ></i>
            </td>
            <td>
              <input
                placeholder="Description"
                type="text"
                class="login-inputs"
                style="border: 0; margin: 0"
                v-model="rule.content"
                v-on:change="emit"
              />
            </td>
            <td>
              <i
                class="far fa-trash-alt"
                style="color: #832626; cursor: pointer"
                v-on:click="removeRule(rule.content)"
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
  props: ["rules"],
  data: function () {
    return {
      newRules: [],
    };
  },
  mounted() {
    if (this.rules) {
      this.newRules = this.rules;
    }
  },
  methods: {
    emit: function () {
      let result = true;
      for (let rule of this.newRules) {
        if (!rule.content) {
          result = false;
        }
      }
      console.log(result);
      console.log(this.newRules);

      let retVal = {
        data: this.newRules,
        result: result,
      };
      this.$emit("ruleupdated", retVal);
    },
    addPositiveRule: function () {
      let newRule = {
        content: "",
        isEnforced: true,
      };
      this.newRules.push(newRule);
      this.emit();
    },
    addNegativeRule: function () {
      let newRule = {
        content: "",
        isEnforced: false,
      };
      this.newRules.push(newRule);
      this.emit();
    },
    removeRule: function (content) {
      for (var rule of this.rules) {
        if (rule.content === content) {
          this.newRules.pop(rule);
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
