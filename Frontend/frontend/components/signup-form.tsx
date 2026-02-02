import { cn } from "@/lib/utils"
import { Button } from "@/components/ui/button"
import { Card, CardContent } from "@/components/ui/card"
import {
  Field,
  FieldDescription,
  FieldGroup,
  FieldLabel,
} from "@/components/ui/field"
import { Input } from "@/components/ui/input"
import { useAuth } from "@/hooks/use-auth"
import { registerSchema} from "@/schemas/auth"
import { useRouter } from "next/navigation"
import { useState } from "react"
import { toast } from "sonner"
import Image from "next/image"

export function SignupForm({
  className,
  ...props
}: React.ComponentProps<"div">) {

  const { register } = useAuth()
  const router = useRouter()
  const [isLoading, setIsLoading] = useState(false)

  const handleSubmit = async (e: React.BaseSyntheticEvent<object, HTMLFormElement, HTMLFormElement>) => {
    e.preventDefault()
    setIsLoading(true)

    const formData = new FormData(e.currentTarget)
    const username = formData.get('username') as string
    const password = formData.get('password') as string
    const confirmPassword = formData.get('confirm-password') as string

    try {
          // Validar con Zod
          const validatedData = registerSchema.parse({ username, password, confirmPassword })

          // Intentar login
          await register(validatedData)
          
          toast.success('¡Bienvenido!', {
            description: 'Inicio de sesión exitoso',
          })
          
          router.push('/editorial') // o la ruta de tu dashboard
        } catch (error) {

          console.log(error) // Mejorar el manejo de errores

        } finally {
          setIsLoading(false)
        }
      }
  return (
    <div className={cn("flex flex-col gap-6", className)} {...props}>
      <Card className="overflow-hidden p-0">
        <CardContent className="grid p-0 md:grid-cols-2">
          <form className="p-6 md:p-8" onSubmit={handleSubmit}>
            <FieldGroup>
              <div className="flex flex-col items-center gap-2 text-center">
                <h1 className="text-2xl font-bold">Create your account</h1>
              </div>
              <Field>
                <FieldLabel htmlFor="username">Username</FieldLabel>
                <Input
                  id="username"
                  type="text"
                  name="username"
                  required
                />
              </Field>
              <Field>
                <Field className="grid grid-cols-2 gap-4">
                  <Field>
                    <FieldLabel htmlFor="password">Password</FieldLabel>
                    <Input id="password" name="password" type="password" required />
                  </Field>
                  <Field>
                    <FieldLabel htmlFor="confirm-password">
                      Confirm Password
                    </FieldLabel>
                    <Input id="confirm-password" name="confirm-password" type="password" required />
                  </Field>
                </Field>
                <FieldDescription>
                  Must be at least 6 characters long.
                </FieldDescription>
              </Field>
              <Field>
                <Button type="submit" disabled={isLoading}>Create Account</Button>
              </Field>
              <FieldDescription className="text-center">
                Already have an account? <a href="/login">Sign in</a>
              </FieldDescription>
            </FieldGroup>
          </form>
          <div className="bg-muted relative hidden md:block">
            <Image
              src="/ortadaRegister.jpg"
              alt="Imagen descriptiva"
              fill // Esto reemplaza el h-full w-full si el padre es relative
              className="object-cover dark:brightness-[0.2] dark:grayscale"
              priority // Úsalo si es la imagen principal para mejorar el LCP
            />
          </div>
        </CardContent>
      </Card>
    </div>
  )
}
