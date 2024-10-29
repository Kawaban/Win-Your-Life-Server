from rest_framework import serializers

from modules.authentication.models import CustomUser


from rest_framework_simplejwt.serializers import TokenObtainPairSerializer


class RegisterSerializer(serializers.Serializer):
    password = serializers.CharField(write_only=True, required=True)
    email = serializers.EmailField(required=True)
    nickname = serializers.CharField(required=True)

    def create(self, validated_data):
        user = CustomUser.objects.create(email=validated_data["email"])

        user.set_password(validated_data["password"])
        user.save()

        return user


class ActivationSerializer(serializers.Serializer):
    token = serializers.CharField(required=True)


class CustomTokenObtainPairSerializer(TokenObtainPairSerializer):
    @classmethod
    def get_token(cls, user):
        token = super().get_token(user)
        # Add custom claims
        token["email"] = user.email
        return token
