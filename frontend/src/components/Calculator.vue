<template>
  <div class="calculator">
    <table class="center">
      <tr>
        <td>
          <label for="amount_input">Amount</label>
          <input id="amount_input" name="amount" v-model.number="calculator.amount" v-on:change="calculatorStateChanged">
        </td>
        <td>
          <label for="from_select">From</label>
          <select v-model="calculator.from" id="from_select" v-on:change="calculatorStateChanged">
            <option v-for="option in fromCurrencies(calculator.to)" v-bind:value="option" v-bind:key="option">
              {{ option }}
            </option>
          </select>
        </td>
        <td>
          <label for="to_select">To</label>
          <select v-model="calculator.to" id="to_select" v-on:change="calculatorStateChanged">
            <option v-for="option in toCurrencies(calculator.from)" v-bind:value="option" v-bind:key="option">
              {{ option }}
            </option>
          </select>
        </td>
      </tr>
    </table>
    <h1 v-if="calculator.resultCurrency.length > 0">{{ calculator.resultAmount }} {{  calculator.resultCurrency }}</h1>
  </div>
</template>

<script>
import Client from '../client';
import Calculator from '../calculator';

const client = new Client('http://localhost:9900');

export default {
  name: 'Calculator',
  data () {
    return {
      currencies: [],
      calculator: new Calculator(client)
    };
  },
  mounted () {
    client.getCurrencies().then(response => {
      console.log(response.data);
      this.currencies = response.data['currencies'];
    }).catch(err => {
      this.$root.showErrorView(err);
    });
  },
  methods: {
    calculatorStateChanged: function (event) {
      this.calculator.onPropertiesChanged(event).catch(err => {
        this.$root.showErrorView(err);
      });
    },
    fromCurrencies: function (toCurrency) {
      return this.currencies.filter(function (c) {
        return c !== toCurrency;
      });
    },
    toCurrencies: function (fromCurrency) {
      return this.currencies.filter(function (c) {
        return c !== fromCurrency;
      });
    }
  }
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
button.default {
  background-color: #4CAF50;
  border: none;
  color: white;
  padding: 8px 16px;
  text-align: center;
  text-decoration: none;
  display: inline-block;
  font-size: 16px;
  margin: 4px 2px;
  cursor: pointer;
  border-radius: 6px;
}
select {
  border: 1px solid lightgray;
  padding: 8px 16px;
  border-radius: 6px;
}
input {
  border: 1px solid lightgray;
  padding: 8px 16px;
  border-radius: 6px;
}
label {
  text-align: left;
  color: black;
  font-size: small;
}
label, input {
  display: block;
}
table.center {
  margin-left: auto;
  margin-right: auto;
  border-collapse: collapse;
  margin-top: 100px;
}
td {
  padding: 20px;
}
h1 {
  font-weight: normal;
  font-size: large;
  text-align: center;
}
</style>
