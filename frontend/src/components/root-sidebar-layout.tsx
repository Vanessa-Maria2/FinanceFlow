import { Money03Icon } from "hugeicons-react";
import { Button } from "@/components/ui/button";
import { DefaultAvatar } from "@/components/default-avatar";
import { Separator } from "@/components/ui/separator"
import { ModeToggle  } from '@/components/theme-changer'

export const Sidebar = () => {
    return (
        <div className="grid grid-rows-[1fr_auto]">
            <div className="flex flex-col justify-start py-5">
                <div className="flex justify-center">
                    <DefaultAvatar />
                </div>

                <Separator className=" w-[75%] mx-auto my-5 align-middle" />
                
                <div className="flex flex-col gap-3">
                    <Button variant="ghost" className="flex gap-3 justify-start">
                        <Money03Icon />
                        <p>Records</p>
                    </Button>
                </div>
            </div>

            <div>
                <ModeToggle />
            </div>
        </div>
    );
}