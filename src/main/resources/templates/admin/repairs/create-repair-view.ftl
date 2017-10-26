<#import "/spring.ftl" as spring/>
<!DOCTYPE html>

<html>


<head>
      <#include "/base_libraries/head.ftl"/>
    <link rel="stylesheet" type="text/css" href="/css/serviceReg.css">
</head>
<body>
<#include "/admin/menu_bar.ftl"/>
<div class="col-md-1"></div>
<div class="col-md-10">
    <h1>New Service Form</h1><br>
    <form action="/admin/create-repair" method="post" name="repairForm">

        <div class="col-md-12">
            <div class="side">
            <span style="color: #4CAF50; font-weight: bold; font-size:14px">${message!""}</span>
            <span style="color: red; font-weight: bold; font-size:14px">${errorMessage!""}</span>
                <div class="col-md-12">
                   <div class="col-md-6">
                <@spring.bind "repairForm.plate"/>
                 <#list spring.status.errorMessages as error>
                 <span style="color:red">${error}</span>
                </#list>
                <label for="Plate">Plate</label>
                    <input type="text" id="plate" name="plate" placeholder="Type the plate..(ABC-1234)" >
                <br/>
                </div>
                <div class="col-md-6">
                                 <@spring.bind "repairForm.status"/>
                                <#list spring.status.errorMessages as error>
                                    <span style="color:red">${error}</span>
                                </#list>
                                 <label for="Status of Service">Status of Service</label>
                            <select id="statusServ" name="status" >
                                    <option value="0" ${pending!""}>Pending</option>
                                    <option value="1" ${inProgress!""}>In progress</option>
                                    <option value="2" ${completed!""}>Completed </option>
                            </select>
                 </div>
             </div>
                <div class="col-md-12">
                <div class="col-md-6">
                <@spring.bind "repairForm.repairDate"/>
                <#list spring.status.errorMessages as error>
                    <span style="color:red">${error}</span>
                </#list>
                <label for="dateService">Service Date</label>
            <input type="datetime-local" id="dateServ" name="repairDate" placeholder="Select a date" value=${repairForm.repairDate!""} >
        </div>


         </div>



                <div class="col-md-12">
                <div class="col-md-6">
                <@spring.bind "repairForm.type"/>
                <#list spring.status.errorMessages as error>
                    <span style="color:red">${error}</span>
                </#list>
                <label for="Type of Service">Type of Service</label>
            <select id="typeServ" name="type" required>
                    <option value="true" ${long!""}>Long</option>
                    <option value="false" ${short!""}>Short</option>
            </select>
                    </div>
                <div class="col-md-6">
                <@spring.bind "repairForm.cost"/>
                <#list spring.status.errorMessages as error>
                    <span style="color:red">${error}</span>
                </#list>
                <label for="Cost">Service Cost</label>
            <input type="text" id="costServ" name="cost" placeholder="Type cost of Service..(123.45)" value=${repairForm.cost!""}>
                    </div>


                </div>
                &nbsp;
            <div class="serviceRegistration">
              <@spring.bind "repairForm.description"/>
                  <#list spring.status.errorMessages as error>
                  <span style="color:red">${error}</span>
              </#list>
            <textarea name="description" class="form-control" rows="4" id="comment" placeholder="Type comments for the service here(255 characters limit).." value="${repairForm.description!""}" >${repairForm.description!""}</textarea>
                </div>
                <input type="submit" value="Submit"><br><br>



            </div>

        </div>

    </form>
</div>
<div class="col-md-1"></div>

<#include "/base_libraries/footer.ftl"/>
<#include "/base_libraries/js.ftl"/>

</body>

</html>