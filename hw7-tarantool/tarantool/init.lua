local function init()
    local s = box.schema.create_space('dialog', {
        if_not_exists = true,
    })
    box.schema.sequence.create('id_seq',{ min=1, start=1, if_not_exists = true })
    s:format({
           { name = 'id', type = 'unsigned' },
           { name = 'dialog_id', type = 'UUID' },
           { name = 'from', type = 'UUID' },
           { name = 'text', type = 'string' },
           { name = 'create_at', type = 'DateTime' }
    })
    s:create_index('primary', {
        type = 'tree',
        sequence = 'id_seq',
	    unique = true,
        parts = {'id'},
        if_not_exists = true,
    })

    s:create_index('secondary', {
        parts = {'dialog_id'},
        unique = false,
        if_not_exists = true
    })

end


function addMessage(dialogId, from, text, createAt)
	local dialog = box.space.dialog
	return dialog:insert{nil, dialogId, from, text, createAt}
end

function getDialog(dialogId)
    local dialog = box.space.dialog
    return dialog.index.secondary:select{dialogId}
end


init()