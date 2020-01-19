<template>
  <div class="fees">
    <div class="content">
      <table class="center">
        <thead>
          <tr style="border-bottom: 1px solid black">
            <td>
             <label for="from_select">From</label>
               <select v-model="fromCurrency" id="from_select">
                  <option v-for="option in fromCurrencies(toCurrency)" v-bind:value="option" v-bind:key="option">
                    {{ option }}
                  </option>
               </select>
            </td>
            <td>
              <label for="to_select">To</label>
               <select v-model="toCurrency" id="to_select">
                 <option v-for="option in toCurrencies(fromCurrency)" v-bind:value="option" v-bind:key="option">
                   {{ option }}
                 </option>
               </select>
            </td>
            <td>
              <label for="fee_input">Fee</label>
              <input id="fee_input" name="fee" v-model.number="fee">
            </td>
            <td class="button">
              <button id="add" class="add" v-on:click="onAdd($event)" :disabled="fees.isLoading">Add</button>
            </td>
          </tr>
        </thead>
        <tbody>
          <tr class="space"></tr>
          <tr v-for="f in fees.feeList" v-bind:key="f.id">
            <td>{{ f['fromCurrency'] }}</td>
            <td>{{ f['toCurrency'] }}</td>
            <td>{{ f['fee'] }}</td>
            <td class="button">
              <button id="remove" class="remove" v-on:click="onRemove(f.id, $event)" :disabled="fees.isLoading">Remove</button>
            </td>
         </tr>
        </tbody>
      </table>
      <div class="footer">
        <button id="prev" class="prev" v-on:click="onPrev" :disabled="fees.isLoading">Prev</button>
        <button id="next" class="next" v-on:click="onNext" :disabled="fees.isLoading">Next</button>
        <h1 class="center" v-if="fees.totalPages > 0">{{ fees.currentPage + 1 }}/{{ fees.totalPages }}</h1>
      </div>
    </div>
  </div>
</template>

<script>
import Fees from '../fees';
import Client from '../client';

const client = new Client('http://localhost:9900');

export default {
  name: 'Fees',
  data () {
    return {
      currencies: [],
      fromCurrency: '-',
      toCurrency: '-',
      fee: 0.0,
      fees: new Fees(client)
    };
  },
  mounted () {
    client.getCurrencies().then(response => {
      console.log(response.data);
      this.currencies = response.data['currencies'].slice();
      if (this.currencies.length > 0) {
        this.fromCurrency = this.currencies[0];
        this.toCurrency = this.currencies[0];
      }

      this.fees.firstPage().catch(err => {
        this.$root.showErrorView(err);
        this.fees.isLoading = false;
      });
    }).catch(err => {
      this.$root.showErrorView(err);
      this.fees.isLoading = false;
    });
  },
  methods: {
    onRemove: function (id, event) {
      console.log(`clicked id = ${id}`);
      this.fees.remove(id);
    },
    onAdd: function (event) {
      console.log(event);
      this.fees.add(this.fromCurrency, this.toCurrency, this.fee).catch(err => {
        this.$root.showErrorView(err);
        this.fees.isLoading = false;
      });
    },
    onNext: function (event) {
      console.log(event);
      this.fees.nextPage().catch(err => {
        this.$root.showErrorView(err);
        this.fees.isLoading = false;
      });
    },
    onPrev: function (event) {
      console.log(event);
      this.fees.prevPage().catch(err => {
        this.$root.showErrorView(err);
        this.fees.isLoading = false;
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
.footer {
  display: inline-block;
  padding-bottom: 25px;
  padding-top: 50px;
  margin-left: auto;
  margin-right: auto;
  width: 800px;
}
.content {
  margin-left: auto;
  margin-right: auto;
  width: 800px;
}
button {
  border: none;
  color: white;
  text-align: center;
  text-decoration: none;
  font-size: 16px;
  cursor: pointer;
  width: 85px;
}
button.next {
  background-color: lightslategray;
  float: right;
  margin-right: 30px;
}
button.prev {
  background-color: lightslategray;
  float: left;
}
button.add {
  background-color: #3d8bcd;
}
button.remove {
  background-color: #db524c;
}
select, input, button {
  display: block;
  /* padding: 8px 16px; */
  border-radius: 6px;
  height: 45px;
}
select, input {
  border: 1px solid lightgray;
}
input {
    padding: 0px 10px;
}
select {
    padding: 5px 10px;
    width: 160px;
    line-height: 26px;/* safari doesn't use height but fiddle with line-height until it looks right*/
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
  border-collapse: collapse;
  width: 100%;
  margin-top: 100px;
}
tr.space {
  height: 35px;
}
tr.space.boreder-bottom {
  border-bottom: 1px solid black;
}
td {
  padding-bottom: 35px;
}
td.data {
  vertical-align: bottom;
  text-align: left;
}
td.button {
  vertical-align: bottom;
  text-align: left;
}
h1 {
  font-weight: normal;
  font-size: large;
}
h1.center {
  margin-left: auto;
  margin-right: auto;
  text-align: center;
}
</style>
