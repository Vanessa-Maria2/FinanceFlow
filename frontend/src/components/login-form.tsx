import { Button } from '@/components/ui/button';
import { Input } from '@/components/ui/input';
import { Label } from '@radix-ui/react-label';
import { FormEvent } from 'react';
import { signIn } from "next-auth/react"

export default function LoginForm() {

    async function onSubmit(e: FormEvent<HTMLFormElement>) {
        e.preventDefault()

        const formData = new FormData(e.currentTarget)

        const data = {
            email: formData.get("email"),
            password: formData.get("password")
        }

        const response = await signIn("credentials", { ...data, callbackUrl: '/home' });
        if (response?.error) {
          console.log("Email ou senha inválidos. Tente novamente.");
      } else {
          window.location.href = response?.url || "/home";
      }    }

    return (
        <form onSubmit={onSubmit} className="bg-white p-12 rounded-lg w-96 max-w-full flex justify-center items-center flex-col gap-2">
            <h2 className="font-bold text-xl mb-3 text-gray-900">Faça o seu login</h2>
            <div>
                <Label htmlFor="email" className="text-sm font-semibold text-gray-700">Email</Label>
                <Input id="email" type="email" placeholder="Email" name="email" />
            </div>

            <div>
                <Label htmlFor="password" className="text-sm font-semibold text-gray-700">Password</Label>
                <Input id="password" type="password" placeholder="Password" name="password" />
            </div>

            <div className="flex justify-center items-center">
                <Button type="submit">
                    Save
                </Button>
            </div>
        </form>
    );
}
