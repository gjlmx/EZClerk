<template>
  <el-container>
    <el-aside width="20%" color='Warning'>
      <span>案件列表</span>
      <el-menu :default-openeds="['1','2','3']">
        <el-submenu index="1">
        <template slot="title"><i class="el-icon-message"></i>发诉案件</template>
          <el-menu-item v-for="oneifcaselist in anjianlist" :index=oneifcaselist.index :key = oneifcaselist.id>{{oneifcaselist.name}}
          </el-menu-item>
        </el-submenu>
        <el-submenu index="2">
          <template slot="title"><i class="el-icon-message"></i>审理案件</template>
          <el-menu-item v-for="oneifcaselist in anjianlist" :index=oneifcaselist.index :key = oneifcaselist.id>{{oneifcaselist.name}}
          </el-menu-item>
        </el-submenu>
        <el-submenu index="3">
          <template slot="title"><i class="el-icon-message"></i>送达案件</template>
          <el-menu-item v-for="oneifcaselist in anjianlist" :index=oneifcaselist.index :key = oneifcaselist.id>{{oneifcaselist.name}}
          </el-menu-item>
        </el-submenu>
      </el-menu>
    </el-aside>
    <el-container>
      <el-header height="200px">


      </el-header>
      <el-main height="100%">
        <el-table
          border
          :data="tableData"
          style="width: 100%"
          :row-class-name="tableRowClassName"
        >
          <el-table-column

            prop="litiPartType"
            label="诉讼地位"
            width="50">
          </el-table-column>
          <el-table-column
            fixed
            prop="name"
            label="名称"
            width="170"
          >
          </el-table-column>
          <el-table-column
            prop="lawsuitPartiesType"
            label="类型"
            width="120">
          </el-table-column>
          <el-table-column
            prop="phoneNum"
            label="联系电话"
            width="120">
          </el-table-column>
          <el-table-column
            prop="address"
            label="地址"
            width="300">
          </el-table-column>
          <el-table-column
            prop="zip"
            label="代理人信息"
            width="300">
          </el-table-column>
          <el-table-column
            fixed="right"
            label="操作"
            width="100">
            <template slot-scope="scope">
              <el-button @click="handleClick(scope.row)" type="text" size="small">编辑</el-button>
              <el-button type="text" size="small" @click="handleDelSdr(scope.row)">删除</el-button>
            </template>
          </el-table-column>
        </el-table>
        <el-button type="success" icon="el-icon-plus" @click="dialogFormVisible = true" plain>原告</el-button>
        <el-button type="warning" icon="el-icon-plus" @click="dialogFormVisible = true" plain>被告</el-button>
        <el-button type="info" icon="el-icon-plus" @click="dialogFormVisible = true" plain>第三人</el-button>
      </el-main>
      <el-dialog title="添加当事人信息" :visible.sync="dialogFormVisible">
        <el-form ref="form" :model="form" label-width="80px">
          <el-form-item label="活动名称">
            <el-input v-model="form.name"></el-input>
          </el-form-item>
          <el-form-item label="活动区域">
            <el-select v-model="form.region" placeholder="请选择活动区域">
              <el-option label="区域一" value="shanghai"></el-option>
              <el-option label="区域二" value="beijing"></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="活动时间">
            <el-col :span="11">
              <el-date-picker type="date" placeholder="选择日期" v-model="form.date1" style="width: 100%;"></el-date-picker>
            </el-col>
            <el-col class="line" :span="2">-</el-col>
            <el-col :span="11">
              <el-time-picker type="fixed-time" placeholder="选择时间" v-model="form.date2"
                              style="width: 100%;"></el-time-picker>
            </el-col>
          </el-form-item>
          <el-form-item label="即时配送">
            <el-switch v-model="form.delivery"></el-switch>
          </el-form-item>
          <el-form-item label="活动性质">
            <el-checkbox-group v-model="form.type">
              <el-checkbox label="美食/餐厅线上活动" name="type"></el-checkbox>
              <el-checkbox label="地推活动" name="type"></el-checkbox>
              <el-checkbox label="线下主题活动" name="type"></el-checkbox>
              <el-checkbox label="单纯品牌曝光" name="type"></el-checkbox>
            </el-checkbox-group>
          </el-form-item>
          <el-form-item label="特殊资源">
            <el-radio-group v-model="form.resource">
              <el-radio label="线上品牌商赞助"></el-radio>
              <el-radio label="线下场地免费"></el-radio>
            </el-radio-group>
          </el-form-item>
          <el-form-item label="活动形式">
            <el-input type="textarea" v-model="form.desc"></el-input>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="onSubmit">立即创建</el-button>
            <el-button>取消</el-button>
          </el-form-item>
        </el-form>
      </el-dialog>

      <el-footer direction="horizontal">
        <el-select v-model="value7" multiple filterable>
          <el-option-group
            v-for="group in options3"
            :key="group.label"
            :label="group.label">
            <el-option
              v-for="item in group.options"
              :key="item.value"
              :label="item.label"
              :value="item.value">
            </el-option>
          </el-option-group>
        </el-select>
        <el-button type="primary" icon="el-icon-edit">文书生成</el-button>
      </el-footer>
    </el-container>
    <el-aside width="20%">


      <el-steps direction="vertical" :active=stepActive>
        <el-step
          v-for="(stepData,index) in stepDatas"
          :description="stepData.description"
          :title="stepData.title"
          :key="index"
        ></el-step>
      </el-steps>
    </el-aside>
  </el-container>
</template>

<script>
  export default {
    methods: {
      tableRowClassName({row, rowIndex}) {
        if (row.litiPartType === "原告") {
          return 'success-row';
        } else if (row.litiPartType === "被告") {
          return 'warning-row';
        }
        return '';
      },
      handleClick(row) {
        this.dialogFormVisible = true;
        this.form.name = row.name;
        console.log(row);
      },
      handleDelSdr(row) {
        console.log(this.tableData.pop());
      },
      onSubmit() {
        console.log();
      },
      checkList: ['选中且禁用', '复选框 A']
    },
    data() {
      return {
        tableData: [{
          litiPartType: '原告',
          name: '王小虎',
          lawsuitPartiesType: '上海',
          phoneNum: '1231231313',
          address: '上海市普陀区金沙江路 1518 弄',
          zip: 200333
        }, {
          litiPartType: '原告',
          name: '王小虎',
          lawsuitPartiesType: '上海',
          phoneNum: '1231231313',
          address: '上海市普陀区金沙江路 1518 弄',
          zip: 200333
        }, {
          litiPartType: '被告',
          name: '王小虎',
          lawsuitPartiesType: '上海',
          phoneNum: '1231231313',
          address: '上海市普陀区金沙江路 1518 弄',
          zip: 200333
        }, {
          litiPartType: '被告',
          name: '王小虎',
          lawsuitPartiesType: '上海',
          phoneNum: '1231231313',
          address: '上海市普陀区金沙江路 1518 弄',
          zip: 200333
        }, {
          litiPartType: '第三人',
          name: '王小虎',
          lawsuitPartiesType: '上海',
          phoneNum: '1231231313',
          address: '上海市普陀区金沙江路 1518 弄',
          zip: 200333
        }, {
          litiPartType: '第三人',
          name: '王小虎',
          lawsuitPartiesType: '上海',
          phoneNum: '1231231313',
          address: '上海市普陀区金沙江路 1518 弄',
          zip: 200333
        }],
        dialogTableVisible: false,
        dialogFormVisible: false,
        labelPosition: 'right',
        form: {
          name: '',
          region: '',
          date1: '',
          date2: '',
          delivery: false,
          type: [],
          resource: '',
          desc: ''
        },
        formLabelWidth: '120px',
        activeName: 'first',
        options3: [{
          label: '发诉材料',
          options: [{
            value: 'songdahuizheng',
            label: '送达回证'
          }, {
            value: 'yingsutongzhishu',
            label: '应诉通知书'
          }, {
            value: 'chuanpiao',
            label: '传票'
          }, {
            value: 'xzzxtzs',
            label: '协助执行通知书'
          }]
        }, {
          label: '笔录材料',
          options: [{
            value: 'kaitingbilu',
            label: '开庭笔录'
          }, {
            value: 'tiaojiebilu',
            label: '调解笔录'
          }, {
            value: 'xunwenbilu',
            label: '询问笔录'
          }]
        }, {
          label: '裁判文书',
          options: [{
            value: 'baoquancaiding',
            label: '保全裁定'
          }, {
            value: 'chesucaiding',
            label: '撤诉裁定'
          }, {
            value: 'minshitiaojieshu',
            label: '民事调解书'
          }, {
            value: 'minshipanjueshu',
            label: '民事判决书'
          }]
        }],
        value7: '',
        anjianlist: [{
          name: '1111', index: '1'
        }, {
          name: '222', index: '1'
        }],
        stepDatas: [
          {description: 'jsifjsjfosj', title: "1"},
          {description: 'sdfsdf', title: "步骤er"},
          {description: 'sdfsdf', title: "步骤3"},
          {description: 'sdfsdf', title: "步骤4"},
          {description: 'sdfsdf', title: "步骤6"},
          {description: 'sdfsdf', title: "步骤5"},
        ],

        stepActive: 5


      }
    }

  }
</script>

<style>
  .el-table .warning-row {
    background: oldlace;
  }

  .el-table .success-row {
    background: #f0f9eb;
  }
</style>
