import React, { useState } from "react";
import { Menu, MenuItem, Button } from "@mui/material";
import ChevronRightIcon from "@mui/icons-material/ChevronRight";

const QueueAllocation = () => {
    const [anchorEl, setAnchorEl] = useState(null);
    const [queues, setQueues] = useState([
        { id: 1, name: "Queue 1" },
        { id: 2, name: "Queue 2" },
        { id: 3, name: "Queue 3" },
    ]);

    const openQueuesMenu = (event) => {
        setAnchorEl(event.currentTarget);
    };

    const handleQueueClick = (queueId) => {
        console.log("Selected Queue ID:", queueId);
        setAnchorEl(null); // Close the menu after selection
        // You can now pass this queueId to fetchQueueUsers or any other function
    };

    const handleClose = () => {
        setAnchorEl(null);
    };

    return (
        <div>
            <Button
                variant="contained"
                onClick={openQueuesMenu}
                endIcon={<ChevronRightIcon />}
            >
                Allocate Request
            </Button>

            <Menu
                anchorEl={anchorEl}
                open={Boolean(anchorEl)}
                onClose={handleClose}
            >
                {queues.map((queue) => (
                    <MenuItem
                        key={queue.id}
                        onClick={() => handleQueueClick(queue.id)}
                    >
                        {queue.name}
                    </MenuItem>
                ))}
            </Menu>
        </div>
    );
};

export default QueueAllocation;
