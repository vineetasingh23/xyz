const formatQueuePath = (name) => {
    return name.replace(/\s+/g, '').toLowerCase().replace(/-/g, '_');
};

 const queuePath = value.queueId === "0" ? 'queue/workflow' : `queue/${formatQueuePath(value.name)}`;
                    const fullPath = `/${queuePath}`;

backgroundColor: isActive(queuePath) ? 'sideBarClickColor' : 'inherit',
                               
