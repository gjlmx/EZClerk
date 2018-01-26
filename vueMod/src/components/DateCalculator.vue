<template>
  <div id = "dateCalculator">
  <el-container>
    <el-header>
      <div class="block">
        <a>起始日</a>
        <el-date-picker
          v-model="startDate"
          type="date"
          placeholder="选择日期"
          @change="shijianjisuan"
        >
        </el-date-picker>

        <a>期间:</a>
        <el-select v-model="days" placeholder="下拉选择或输入"
                   filterable
                   clearable
                   allow-create
                   @change="shijianjisuan">
          <el-option
            v-for="item in timePeriods"
            :key="item.value"
            :label="item.label"
            :value="item.value">
          </el-option>

        </el-select>
      </div>
    </el-header>
    <a>根据2018年度放假安排自动调整</a>
    <el-main>
      结果日期: {{tarDate}}
    </el-main>
  </el-container>
  </div>
</template>
<script>
  export default {
    data() {
      return {
        startDate: new Date(),
        tarDate: '',
        days: '',
        timePeriods: [{
          value: '20',
          label: '简易程序开庭-20日'
        }, {
          value: '38',
          label: '普通程序开庭-38日'
        }, {
          value: '75',
          label: '公告生效-75日'
        }, {
          value: '93',
          label: '公告开庭-93日'
        }, {
          value: '60',
          label: '公告送达-60日'
        }]

      };
    },
    filter: {},
    methods: {
      shijianjisuan() {
        const d = new Date(this.startDate);
        if (this.days !== 0) {
          d.setDate(d.getDate() + Number(this.days));
        }
        var m = d.getMonth() + 1;
        var xq = "  星期" + "日一二三四五六".charAt(d.getDay());
        var subDate = d.getFullYear() + '-' + m + '-' + d.getDate();

        if (this.isWorkDay(subDate, d)) {
          this.tarDate = subDate + xq;
        } else {
          while (!this.isWorkDay(d)) {
            d.setDate(d.getDate() + Number(1));
          }
          var nextdxq = "  星期" + "日一二三四五六".charAt(d.getDay());
          var nextsubDate = d.getFullYear() + '-' + m + '-' + d.getDate();
          this.tarDate = subDate + xq + '，非工作日。 下一工作日为：' + nextsubDate + nextdxq;
        }
      },
      isWorkDay(date1) {
        var date = new Date(date1);
        var m = date.getMonth() + 1;
        var subDate = date.getFullYear() + '-' + m + '-' + date.getDate();
        var jrdate_2018 = ["2018-1-1", "2018-2-15", "2018-2-16", "2018-2-17", "2018-2-18", "2018-2-19", "2018-2-20", "2018-2-21", "2018-4-5", "2018-4-6", "2018-4-7", "2018-4-29", "2018-4-30", "2018-5-1", "2018-6-16", "2018-6-17", "2018-6-18", "2018-9-22", "2018-9-23", "2018-9-24", "2018-10-1", "2018-10-2", "2018-10-3", "2018-10-4", "2018-10-5", "2018-10-6", "2018-10-7"];
        var txr_2018 = ["2018-2-11", "2018-2-24", "2018-4-8", "2018-4-28", "2018-9-29", "2018-9-30"];//2018年调休日
        var is_jr_2018 = this.inarray(subDate, jrdate_2018);
        var is_tx_2018 = this.inarray(subDate, txr_2018);
        if (is_jr_2018) {
          return false; //"节假日";
        } else if (is_tx_2018) {
          return true;//调休日
        }
        var getday = date.getDay();
        if (getday == 0 || getday == 6) {
          return false;// '休息日';
        } else {
          return true;
        }
      },
      inarray(str, array) {
        var j = 0;
        var len = array.length;
        for (j, len; j < len; j++) {
          if (array[j] == str) {
            return true;
          }
        }
        return false;
      }

    }
  };
</script>
<style>
  @import url("//unpkg.com/element-ui@2.0.11/lib/theme-chalk/index.css");
</style>
