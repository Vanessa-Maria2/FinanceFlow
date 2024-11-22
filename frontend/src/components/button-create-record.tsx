import { forwardRef, Ref } from "react";
import { AddCircleHalfDotIcon } from "hugeicons-react"
import { Button } from "@/components/ui/button"

export const ButtonCreateRecord = forwardRef((props, ref: Ref<HTMLButtonElement>) => {
    return (
        <Button ref={ref} variant='default' {...props}>
            <AddCircleHalfDotIcon />
        </Button>
    )
});

ButtonCreateRecord.displayName = "ButtonCreateRecord";