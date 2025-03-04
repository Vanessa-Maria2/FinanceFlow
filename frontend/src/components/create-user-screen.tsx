'use client';
import { SubmitHandler, useForm } from "react-hook-form";
import { zodResolver } from "@hookform/resolvers/zod";
import { Button } from "./ui/button";
import * as z from "zod";
import { Card, CardContent, CardDescription, CardHeader, CardTitle } from "./ui/card";
import { Input } from "./ui/input";
import { Label } from "./ui/label";
import { Separator } from "./ui/separator";
import { toast } from "sonner";

const schema = z.object({
    name: z.string().min(3, "The name must have at least 3 characters"),
    email: z.string().email("Enter a valid email"),
    password: z.string().min(5, "Password must be at least 5 characters long").max(8, "The password must have a maximum of 8 characters"),
});

type FormData = z.infer<typeof schema>;

export function CreateUserScreen() {
    const { register, handleSubmit, formState: { errors }, reset } = useForm<FormData>({
        resolver: zodResolver(schema),
    });

    const onSubmit: SubmitHandler<FormData> = async (data: FormData) => {
        try {
            const res = await fetch('http://localhost:8080/person', {
                method: "POST",
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify(data),
            });

            const responseData = await res.json();

            if (responseData.error) {
                toast.error("Error saving user: " + responseData.error, { style: { backgroundColor: '#e74c3c' } });
            } else {
                toast.success("User registered successfully!", { style: { backgroundColor: '#2ecc71' } });
                reset();
            }

        } catch (error) {
            toast.error("Error connecting to the server", { style: { backgroundColor: '#e74c3c' } });
        }
    }

    return (
        <div className="h-screen w-full flex justify-center items-center">
            <Card className="w-96 p-6 shadow-lg rounded-2xl bg-white">
                <CardHeader>
                    <CardTitle className="text-center text-xl font-bold text-gray-700">FinanceFlow</CardTitle>
                    <CardDescription className="text-center">Create User</CardDescription>
                </CardHeader>
                <CardContent>
                    <Separator className="mb-4" />

                    <form onSubmit={handleSubmit(onSubmit)} className="space-y-4">
                        <div>
                            <Label htmlFor="name" className="text-sm font-semibold text-gray-700">Name</Label>
                            <Input id="name" type="text" placeholder="Name" {...register("name")} />
                            {errors.name && <p className="text-red-500 text-sm mt-1">{String(errors.name.message)}</p>}
                        </div>

                        <div>
                            <Label htmlFor="email" className="text-sm font-semibold text-gray-700">Email</Label>
                            <Input id="email" type="email" placeholder="Email" {...register("email")} />
                            {errors.email && <p className="text-red-500 text-sm mt-1">{String(errors.email.message)}</p>}
                        </div>

                        <div>
                            <Label htmlFor="password" className="text-sm font-semibold text-gray-700">Password</Label>
                            <Input id="password" type="password" placeholder="Password" {...register("password")} />
                            {errors.password && <p className="text-red-500 text-sm mt-1">{String(errors.password.message)}</p>}
                        </div>

                        <div className="flex justify-center items-center">
                            <Button type="submit">
                                Save
                            </Button>
                        </div>
                    </form>
                </CardContent>
            </Card>
        </div>
    )
}