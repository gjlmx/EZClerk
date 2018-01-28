<template>
  <el-container>
    <el-aside width="20%" color='Warning'>
      <span>案件列表</span>
      <el-menu :default-openeds="['1','2','3']">
        <el-submenu index="1">
          <template slot="title"><i class="el-icon-message"></i>发诉案件</template>
          <el-menu-item v-for="oneifcaselist in anjianlist" :index="oneifcaselist.indexxx" :key=oneifcaselist.id @click="caseInfoLoad(oneifcaselist.id)">
            {{oneifcaselist.caseNum}}
          </el-menu-item>
        </el-submenu>
        <!--<el-submenu index="2">-->
          <!--<template slot="title"><i class="el-icon-message"></i>审理案件</template>-->
          <!--<el-menu-item v-for="oneifcaselist in anjianlist" :index=oneifcaselist.index :key=oneifcaselist.id>-->
            <!--{{oneifcaselist.name}}-->
          <!--</el-menu-item>-->
        <!--</el-submenu>-->
        <!--<el-submenu index="3">-->
          <!--<template slot="title"><i class="el-icon-message"></i>送达案件</template>-->
          <!--<el-menu-item v-for="oneifcaselist in anjianlist" :index=oneifcaselist.index :key=oneifcaselist.id>-->
            <!--{{oneifcaselist.name}}-->
          <!--</el-menu-item>-->
        <!--</el-submenu>-->
      </el-menu>
    </el-aside>
    <el-container>
      <el-header height="200px">
        <el-form ref="caseInfoForm" :model="caseInfoForm" label-width="100px" size="mini" :inline="true">
          <el-form-item label="案号" style="width: 300px;">
            <el-input v-model="caseInfoForm.caseNum"></el-input>
          </el-form-item>

          <!--public static  String CASE_CAUSE_HT = "合同纠纷";-->
          <!--public static  String CASE_CAUSE_MJJD = "民间借贷纠纷";-->
          <!--public static  String CASE_CAUSE_SQBQ = "申请诉前财产保全";-->
          <!--public static  String CASE_CAUSE_JRJK = "金融借款合同纠纷";-->
          <!--public static  String CASE_CAUSE_BDDL = "不当得利";-->
          <!--public static  String CASE_CAUSE_ZXYY = "执行异议";-->
          <!--public static  String CASE_CAUSE_XYK = "信用卡纠纷";-->
          <el-form-item label="案由"  style="width: 400px;">
            <el-select v-model="caseInfoForm.anyou" placeholder="请输入案由" filterable>
              <el-option label="合同纠纷ht" value="合同纠纷"></el-option>
              <el-option label="民间借贷纠纷mjjd" value="民间借贷纠纷"></el-option>
              <el-option label="金融借款合同纠纷jrjkht" value="金融借款合同纠纷"></el-option>
              <el-option label="信用卡纠纷xyk" value="信用卡纠纷"></el-option>
              <el-option label="执行异议zxyy" value="执行异议"></el-option>
              <el-option label="申请诉前财产保全sqbq" value="申请诉前财产保全"></el-option>
            </el-select>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="addCase">保存</el-button>
          </el-form-item>
        </el-form>

      </el-header>
      <el-main height="100%">
        <el-table
          border
          :data="tableData"
          style="width: 100%"
          :row-class-name="tableRowClassName"
        >
          <el-table-column

            prop="type"
            label="诉讼地位"
            width="60">
          </el-table-column>
          <el-table-column
            fixed
            prop="name"
            label="名称"
            width="170"
          >
          </el-table-column>
          <el-table-column
            prop="lawsPartType"
            label="类型"
            width="60">
          </el-table-column>
          <el-table-column
            prop="phoneNum"
            label="联系电话"
            width="120">
          </el-table-column>
          <el-table-column
            label="代理人信息"
            width="300">
            <template slot-scope="scope">
              <el-popover trigger="hover" placement="top">
                <p>姓名: {{ scope.row.litiRepresInfo }}</p>
                <div slot="reference" class="name-wrapper">
                  <el-tag size="medium">代理人信息</el-tag>
                </div>
              </el-popover>
            </template>
          </el-table-column>
          <el-table-column
            prop="addr"
            label="地址"
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
        <el-button type="success" icon="el-icon-plus" @click="addLitiParts('原告')" plain>原告</el-button>
        <el-button type="warning" icon="el-icon-plus" @click="addLitiParts('被告')" plain>被告</el-button>
        <el-button type="info" icon="el-icon-plus" @click="addLitiParts('第三人')" plain>第三人</el-button>
      </el-main>
      <el-dialog title="添加当事人信息" :visible.sync="litiPartsInfoFormVisible">
        <el-form ref="litiPartsInfoForm" :model="litiPartsInfoForm" label-width="100px" size="mini" :inline="true">


          <el-form-item label="名称" style="width: 300px;">
            <el-input v-model="litiPartsInfoForm.name"></el-input>
          </el-form-item>
          <el-form-item label="诉讼地位">
            <el-select v-model="litiPartsInfoForm.type" placeholder="请选择诉讼地位">
            </el-select>
          </el-form-item>
          <el-form-item label="当事人类型" style="width: 400px;">
            <el-select v-model="selectLawsPartType" placeholder="请选择当事人类型" @change="litiPartsLawsPartTypeChange">
              <el-option label="公民" value="公民"></el-option>
              <el-option label="法人" value="法人"></el-option>
              <el-option label="其他组织" value="其他组织"></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="联系电话" style="width: 300px;">
            <el-input v-model="litiPartsInfoForm.phone"></el-input>
          </el-form-item>
          <el-form-item label="住址" style="width: 300px;">
            <el-input v-model="litiPartsInfoForm.addr"></el-input>
          </el-form-item>
          <!--<el-form-item label="age" style="width: 300px;">-->
            <!--<el-input v-model="litiPartsInfoForm.age" v-bind:disabled="!lawsPartTypeIsPson"></el-input>-->
          <!--</el-form-item>-->
          <el-form-item label="公民身份号码" style="width: 300px;">
            <el-input v-model="litiPartsInfoForm.gmsfhm" v-bind:disabled="!lawsPartTypeIsPson"
                      @change="getCsrqSex" clearable ></el-input>
          </el-form-item>

          <el-form-item label="民族" style="width: 300px;">
            <el-input v-model="litiPartsInfoForm.minzu" v-bind:disabled="!lawsPartTypeIsPson"></el-input>
          </el-form-item>
          <el-form-item label="性别" style="width: 300px;">
            <el-input v-model="litiPartsInfoForm.sex" v-bind:disabled="!lawsPartTypeIsPson"></el-input>
          </el-form-item>
          <el-form-item label="出生日期" style="width: 300px;">
            <el-date-picker
              v-model="litiPartsInfoForm.csrq"
              type="date"
              v-bind:disabledDate="!lawsPartTypeIsPson"
              placeholder="选择日期">
            </el-date-picker>
          </el-form-item>
          <el-form-item label="信用代码" style="width: 300px;">
            <el-input v-model="litiPartsInfoForm.tyshxydm" v-bind:disabled="lawsPartTypeIsPson"></el-input>
          </el-form-item>
          <el-form-item label="法定代表人" style="width: 300px;">
            <el-input v-model="litiPartsInfoForm.fddbr" v-bind:disabled="lawsPartTypeIsPson"></el-input>
          </el-form-item>


          <el-form-item>
            <el-button type="primary" @click="addLP">保存</el-button>
            <el-button @click="resetLpf">取消</el-button>
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
    created() {
      // this.$api.get('json/litipart', null, r => {
      //   this.tableData = r;
      // })
          this.$api.get("allCase", null, r=>{
            this.anjianlist = r;
            console.log(r)
      });



    },

    methods: {
      caseInfoLoad(caseId){
        this.$api.get("getCaseByid",{id:caseId},r=>{
          this.caseInfoForm = r;
          console.log(r)
        })
      },
      tableRowClassName({row, rowIndex}) {
        if (row.litiPartType === "原告") {
          return 'success-row';
        } else if (row.litiPartType === "被告") {
          return 'warning-row';
        }
        return '';
      },
      handleClick(row) {
        this.litiPartsInfoFormVisible = true;
        this.litiPartsInfoForm.name = row.name;
        console.log(row);
      },
      handleDelSdr(row) {
        console.log(this.tableData.pop());
      },
      onSubmit() {
        console.log();
      },
      addLitiParts(dsrlx) {
        this.litiPartsInfoFormVisible = true;
        this.litiPartsInfoForm = {};
        this.litiPartsInfoForm.type = dsrlx;
      },
      getCsrqSex() {
        var gmsfhm = this.litiPartsInfoForm.gmsfhm;
        console.log(gmsfhm);
        var birthdayno, birthdaytemp
        if (gmsfhm.length == 18) {
          birthdayno = gmsfhm.substring(6, 14)
        } else if (gmsfhm.length == 15) {
          birthdaytemp = gmsfhm.substring(6, 12)
          birthdayno = "19" + birthdaytemp
        } else {
          return;
        }
        var birthday = birthdayno.substring(0, 4) + "-" + birthdayno.substring(4, 6) + "-" + birthdayno.substring(6, 8);
        this.litiPartsInfoForm.csrq = new Date(birthday);
        if (parseInt(gmsfhm.substr(16, 1)) % 2 == 1) {
//男
          this.litiPartsInfoForm.sex = "男";
        } else {
          this.litiPartsInfoForm.sex = "女";
//女
        }
      },
      addCase(){
        this.$api.post("saveCase", this.caseInfoForm, r=>{
          console.log(r)
        });

      },
      addLP() {
        this.$api.post('json/addlitipart', this.litiPartsInfoForm, r => {
          console.log(r)
        })
      },
      resetLpf(){
        // this.$refs["litiPartsInfoForm"].resetFields();
        this.litiPartsInfoForm = {},
          this.litiPartsInfoFormVisible = false;
      }
      ,
      litiPartsLawsPartTypeChange() {
        this.litiPartsInfoForm.lawsPartType = this.selectLawsPartType;
        if (this.selectLawsPartType === "公民") {
          this.litiPartsInfoForm.isPson = true;
          this.lawsPartTypeIsPson = true;
        } else {
          this.litiPartsInfoForm.isPson = false;
          this.lawsPartTypeIsPson = false;
        }
        console.log(this.litiPartsInfoForm.lawsPartType);
      }
      ,
      checkList: ['选中且禁用', '复选框 A']
    },
    data() {
      return {
        tableData: [],
        dialogTableVisible: false,
        litiPartsInfoFormVisible: false,
        labelPosition: 'right',
        selectLawsPartType: '公民',
        lawsPartTypeIsPson: true,
        litiPartsInfoForm: {
          name: null,
          type: "原告",
          lawsPartType: "公民",
          addr: "青岛市崂山区深圳路111号",
          //"litiParts" : null,
          isPson: true,
          age: 23,
          csrq: 1516979636398,
          gmsfhm: "370202192301011111",
          minzu: "汉族",
          sex: "男",
          tyshxydm: null,
          fddbr: null
        },
        formLabelWidth: '120px',
        caseInfoForm:{
            caseNum:'',
            anyou:''
        },
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
        anjianlist: [],
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
