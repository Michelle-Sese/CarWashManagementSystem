function renderStaff(){
   TracomAcademy.Grid.apply({
        contentRender: "content",
        gridTitle: "Staff",
        componentId: "staff",
        url: "staff",
        columns: [{
            header: "Id",
            dataIndex: "id",
            width: 20
        }, {
            header: "Fname",
            dataIndex: "Fname",
            width: 30
        }, {
            header: "Lname",
            dataIndex: "Lname",
            width: 30
            },
             {
                header: "Dept_id",
                dataIndex: "Dept_id",
                width: 30
                    },
        {
            header: "",
            dataIndex: "delete",
            width: 15,
            },
        {
            header: "",
            dataIndex: "edit",
            width: 15,
        },
        ],
        store: [],
        form: {
            id: "staff-form",
            url: "staff",
            items: [{
                label: "ID No.",
                name: "sid",
                id: "staff.sid",
                type: "text"
            },{
                label: "Fname",
                name: "sfname",
                id: "staff.sfname",
                type: "text"
            },{
                label: "Lname",
                name: "slname",
                id: "staff.slname",
                type: "text"
              },{
                label: "Dept_id",
                name: "sdept-id",
                id: "staff.sdept-id",
                type: "text"
             }]

        }
    });
}
